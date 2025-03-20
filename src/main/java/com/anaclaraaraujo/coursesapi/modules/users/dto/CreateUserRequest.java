package com.anaclaraaraujo.coursesapi.modules.users.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String email;
    private String password;
    private String name;
    private String role;
}
