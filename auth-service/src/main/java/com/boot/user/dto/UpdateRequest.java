package com.boot.user.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateRequest {
    private String email;
    private String status;
}
