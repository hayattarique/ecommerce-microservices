package com.boot.user.dto;

import lombok.Data;

@Data
public class UpdateCredentialDto {
    private String email;
    private String oldPassword;
    private String newPassword;
}
