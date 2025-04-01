package com.anaclaraaraujo.coursesapi.modules.courses.useCases;

import com.anaclaraaraujo.coursesapi.exceptions.ValidationException;
import com.anaclaraaraujo.coursesapi.modules.courses.CourseRepository;
import com.anaclaraaraujo.coursesapi.modules.courses.dto.UpdateCourseRequest;
import com.anaclaraaraujo.coursesapi.modules.courses.entities.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public String execute(UUID courseId, UpdateCourseRequest updateCourseRequest) throws Exception {
        Optional<CourseEntity> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new Exception("Curso não encontrado");
        }

        CourseEntity courseEntity = courseOptional.get();

        if (updateCourseRequest.getTitle() != null && updateCourseRequest.getCategory() != null) {
            throw new ValidationException("Não é permitido atualizar title e category ao mesmo tempo");
        }

        if (updateCourseRequest.getTitle() != null) {
            courseEntity.setTitle(updateCourseRequest.getTitle());
        } else if (updateCourseRequest.getCategory() != null) {
            courseEntity.setCategory(updateCourseRequest.getCategory());
        } else {
            throw new ValidationException("É necessário informar pelo menos um campo (title ou category) para atualizar");
        }

        courseRepository.save(courseEntity);

        return courseEntity.getId().toString();
    }
}
