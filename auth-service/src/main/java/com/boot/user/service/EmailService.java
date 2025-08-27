package com.boot.user.service;

import com.boot.user.entities.Users;

public interface EmailService {
    /**
     * Sends an email to the specified user with a file attachment.
     *
     * @param users    the recipient user
     * @param subject  the subject of the email
     * @param fileName the name of the file to attach
     * @return true if the email was sent successfully, false otherwise
     */
    boolean sendEmail(Users users, String subject, String fileName) throws Exception;

}
