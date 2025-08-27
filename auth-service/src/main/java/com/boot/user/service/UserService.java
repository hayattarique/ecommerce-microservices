package com.boot.user.service;

import com.boot.user.dto.AuthResponse;
import com.boot.user.dto.LoginRequest;
import com.boot.user.dto.UpdateCredentialDto;
import com.boot.user.dto.SignupRequest;
import com.boot.user.entities.Users;

public interface UserService {
    /**
     * Creates a new account with the provided client details.
     * A random password will be generated.
     *
     * @param dto request payload for account creation
     * @return true if an account is created successfully, false otherwise
     */
    boolean userRegister(SignupRequest dto);


    /**
     * @param request this for
     * @return response with all the necessary details
     */
    AuthResponse login(LoginRequest request);

    /**
     * @param email  finding user based on email id if user found then process the next step
     * @param status when user found then activate the user or deactivate the user
     * @return success or failed
     */
    String changeAccountStatus(String email, String status);

    /**
     * Changes the password for a user account.
     *
     * @param credentialDto contains the user's email and new password details
     * @return a message indicating whether the password change was successful
     */
    String changePassword(UpdateCredentialDto credentialDto);


    String generateJwtToken(Users users,String deviceId);


}
