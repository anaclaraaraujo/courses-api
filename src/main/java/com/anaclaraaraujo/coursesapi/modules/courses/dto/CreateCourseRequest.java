package com.anaclaraaraujo.coursesapi.modules.courses.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateCourseRequest {
    private String title;
    private String description;
    private UUID instructorId;
}
