package com.anaclaraaraujo.coursesapi.modules.courses;

import com.anaclaraaraujo.coursesapi.modules.courses.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

}
