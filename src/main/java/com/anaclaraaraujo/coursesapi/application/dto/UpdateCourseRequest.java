package com.anaclaraaraujo.coursesapi.application.dto;

import com.anaclaraaraujo.coursesapi.domain.enums.CourseCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseRequest {
    private String title;
    private CourseCategory category;
}
