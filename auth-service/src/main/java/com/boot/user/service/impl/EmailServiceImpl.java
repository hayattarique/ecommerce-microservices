package com.boot.user.service.impl;

import com.boot.user.entities.Users;
import com.boot.user.service.EmailService;
import com.boot.user.utils.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailUtil emailUtil;

    @Override
    public boolean sendEmail(Users user, String subject, String fileName) throws Exception {
        String body = emailUtil.readEmailTemplate(fileName);
        body = body.replace("{USERNAME}", user.getUsername()).replace("{PASSWORD}", user.getPassword());
        String email = user.getEmail();
        return emailUtil.sendEmail(email, subject, body);
    }


}
