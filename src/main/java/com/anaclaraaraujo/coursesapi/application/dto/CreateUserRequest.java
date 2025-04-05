package com.anaclaraaraujo.coursesapi.application.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String email;
    private String password;
    private String name;
    private String role;
}
