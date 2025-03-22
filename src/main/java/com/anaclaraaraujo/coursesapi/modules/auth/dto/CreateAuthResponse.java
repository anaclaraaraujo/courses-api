package com.anaclaraaraujo.coursesapi.modules.auth.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CreateAuthResponse {
    private String token;
    private Date expires_in;
    private Date created_at;
}
