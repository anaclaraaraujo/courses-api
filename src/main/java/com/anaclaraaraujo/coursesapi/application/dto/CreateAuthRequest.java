package com.anaclaraaraujo.coursesapi.application.dto;

import lombok.Data;

@Data
public class CreateAuthRequest {
    private String email;
    private String password;
}
