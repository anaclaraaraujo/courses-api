package com.anaclaraaraujo.coursesapi.modules.courses.useCases;

import com.anaclaraaraujo.coursesapi.modules.courses.CourseRepository;
import com.anaclaraaraujo.coursesapi.modules.courses.entities.CourseCategory;
import com.anaclaraaraujo.coursesapi.modules.courses.entities.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseEntity> execute(String title, String category) {
        if (title != null && !title.isEmpty() && category != null && !category.isEmpty()) {
            return courseRepository.findByTitleContainingIgnoreCaseAndCategory(title, CourseCategory.valueOf(category.toUpperCase()));
        } else if (title != null && !title.isEmpty()) {
            return courseRepository.findByTitleContainingIgnoreCase(title);
        } else if (category != null && !category.isEmpty()) {
            return courseRepository.findByCategory(CourseCategory.valueOf(category.toUpperCase()));
        }
        return courseRepository.findAll();
    }
}