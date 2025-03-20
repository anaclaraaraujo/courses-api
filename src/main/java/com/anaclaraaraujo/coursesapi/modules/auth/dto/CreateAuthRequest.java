package com.anaclaraaraujo.coursesapi.modules.auth.dto;

import lombok.Data;

@Data
public class CreateAuthRequest {
    private String email;
    private String password;
}
