package com.anaclaraaraujo.coursesapi.application.dto;

import com.anaclaraaraujo.coursesapi.domain.enums.CourseCategory;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateCourseRequest {
    private String title;
    private String description;
    private UUID instructorId;
    private CourseCategory category;
}
