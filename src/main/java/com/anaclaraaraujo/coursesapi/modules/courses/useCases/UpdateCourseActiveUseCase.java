package com.anaclaraaraujo.coursesapi.modules.courses.useCases;

import com.anaclaraaraujo.coursesapi.exceptions.ValidationException;
import com.anaclaraaraujo.coursesapi.modules.courses.CourseRepository;
import com.anaclaraaraujo.coursesapi.modules.courses.entities.CourseEntity;
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
