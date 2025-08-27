package com.boot.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SignupRequest {
    @NotBlank(message = "Username cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_\\-.]+$", message = "Username contains invalid characters")
    private String username;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "gender cannot be blank")
    @Pattern(regexp = "MALE|FEMALE|OTHER", message = "Gender must be 'MALE', 'FEMALE', or 'OTHER'")
    private String gender;
    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be exactly 10 digits")
    private String mobileNo;
    @NotBlank(message = "tenantId cannot be blank")
    private String tenantId;
}
