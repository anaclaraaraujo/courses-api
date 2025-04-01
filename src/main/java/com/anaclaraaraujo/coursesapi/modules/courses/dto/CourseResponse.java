package com.anaclaraaraujo.coursesapi.modules.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private UUID id;
    private String title;
    private String description;
    private String category;
    private boolean active;
    private UUID instructorId;
}
