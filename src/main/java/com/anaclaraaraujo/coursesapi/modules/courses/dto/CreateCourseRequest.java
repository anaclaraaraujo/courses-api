package com.anaclaraaraujo.coursesapi.modules.courses.dto;

import com.anaclaraaraujo.coursesapi.modules.courses.entities.CourseCategory;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateCourseRequest {
    private String title;
    private String description;
    private UUID instructorId;
    private CourseCategory category;
}
