package com.anaclaraaraujo.coursesapi.modules.courses.useCases;

import com.anaclaraaraujo.coursesapi.modules.courses.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public void execute(UUID courseId) throws Exception {
        courseRepository
                .findById(courseId)
                .ifPresentOrElse(
                        course -> courseRepository.delete(course),
                        () -> {
                            throw new RuntimeException("Curso não encontrado");
                        }
                );
    }
}
