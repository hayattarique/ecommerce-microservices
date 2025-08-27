package com.boot.user.service.impl;

import com.boot.user.dto.AuthResponse;
import com.boot.user.dto.LoginRequest;
import com.boot.user.dto.SignupRequest;
import com.boot.user.dto.UpdateCredentialDto;
import com.boot.user.entities.Roles;
import com.boot.user.entities.Users;
import com.boot.user.exception.AuthenticationException;
import com.boot.user.repositories.UserRepository;
import com.boot.user.service.EmailService;
import com.boot.user.service.RefreshTokenService;
import com.boot.user.service.UserService;
import com.boot.user.utils.ErrorCode;
import com.boot.user.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.boot.user.utils.PasswordGenerator.generateRandomPassword;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public boolean userRegister(SignupRequest dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            log.error("User registration failed: Email {} already exists.", dto.getEmail());
            throw new AuthenticationException(ErrorCode.USER_ALREADY_EXISTS, "Email already exists: " + dto.getEmail());
        }
        Users users = new Users();
        BeanUtils.copyProperties(dto, users);
        users.setPassword(generateRandomPassword());
        users.setActive("I"); // Set initial status to 'I' (Inactive)
        users.setRole(new Roles(1L));
        userRepository.save(users);
        // Send email notification
        String subject = "Account Registration Successful";
        try {
            emailService.sendEmail(users, subject, "welcome-email.txt");
        } catch (Exception e) {
            log.error("Failed to send registration email to user: {}. Error: {}", users.getEmail(), e.getMessage());
        }
        return users.getId() != null;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (!authenticate.isAuthenticated()) {
            log.error("Failed to authenticate user: {}.", request.getEmail());
            throw new AuthenticationException(ErrorCode.AUTH_FAILED, "Authentication failed for user: " + request.getEmail());
        }
        Users users = BeanUtils.instantiateClass(Users.class);
        BeanUtils.copyProperties(request, users);
        users = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthenticationException(ErrorCode.USER_NOT_FOUND, "User not found with email: " + request.getEmail()));
        String refreshToken = refreshTokenService.generateRefreshToken(users.getId());
        String token = generateJwtToken(users, request.getDeviceId());
        return new AuthResponse(token, refreshToken, users.getEmail(), users.getTenantId(), request.getDeviceId());
    }

    @Override
    @Transactional
    public String changeAccountStatus(String email, String status) {
        Users users = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (!StringUtils.equalsIgnoreCase(users.getActive(), status)) {
            users.setActive(status);
            userRepository.save(users);
            return "Account status updated successfully! The user is now " + users.getActive() + ".";
        }

        return "The account status is already set to " + status + ". No changes made.";
    }

    @Override
    @Transactional
    public String changePassword(UpdateCredentialDto credentialDto) {
        Users user = userRepository.findByEmail(credentialDto.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        if (StringUtils.equals(credentialDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(credentialDto.getNewPassword()));
            user.setActive("A"); // Set active status to 'A' after password change
            userRepository.save(user);
            return "Password changed successfully!";
        }

        return "Password change failed. Old password does not match.";
    }

    @Override
    public String generateJwtToken(Users users,String deviceId) {
        return jwtUtils.generateToken(new User(users.getUsername(),
                users.getPassword(), List.of(new SimpleGrantedAuthority(users.getRole().getRole()))), users.getTenantId(), deviceId);
    }
}
