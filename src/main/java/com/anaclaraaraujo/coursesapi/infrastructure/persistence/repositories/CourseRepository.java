package com.anaclaraaraujo.coursesapi.infrastructure.persistence.repositories;

import com.anaclaraaraujo.coursesapi.domain.enums.CourseCategory;
import com.anaclaraaraujo.coursesapi.domain.entities.CourseEntity;
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