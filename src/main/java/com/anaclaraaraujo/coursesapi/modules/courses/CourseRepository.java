package com.anaclaraaraujo.coursesapi.modules.courses;

import com.anaclaraaraujo.coursesapi.modules.courses.entities.CourseCategory;
import com.anaclaraaraujo.coursesapi.modules.courses.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    List<CourseEntity> findByTitleContainingIgnoreCase(String title);
    List<CourseEntity> findByCategory(CourseCategory category);
    List<CourseEntity> findByTitleContainingIgnoreCaseAndCategory(String title, CourseCategory category);
    Optional<CourseEntity> findById(UUID id);
}