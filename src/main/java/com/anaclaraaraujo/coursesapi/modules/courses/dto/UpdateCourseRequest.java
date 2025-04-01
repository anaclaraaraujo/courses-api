package com.anaclaraaraujo.coursesapi.modules.courses.dto;

import com.anaclaraaraujo.coursesapi.modules.courses.entities.CourseCategory;
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
