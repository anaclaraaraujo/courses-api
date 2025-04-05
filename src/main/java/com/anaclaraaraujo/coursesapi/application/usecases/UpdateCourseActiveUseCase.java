package com.anaclaraaraujo.coursesapi.application.usecases;

import com.anaclaraaraujo.coursesapi.interfaces.exception.ValidationException;
import com.anaclaraaraujo.coursesapi.infrastructure.persistence.repositories.CourseRepository;
import com.anaclaraaraujo.coursesapi.domain.entities.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateCourseActiveUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public String execute(UUID courseId) throws Exception {
        Optional<CourseEntity> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new ValidationException("Curso não encontrado");
        }

        CourseEntity courseEntity = courseOptional.get();
        courseEntity.setActive(!courseEntity.isActive());

        courseRepository.save(courseEntity);
        return "Status atualizado com sucesso!";
    }
}
