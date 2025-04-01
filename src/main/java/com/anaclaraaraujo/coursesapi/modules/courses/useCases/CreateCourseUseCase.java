package com.anaclaraaraujo.coursesapi.modules.courses.useCases;

import com.anaclaraaraujo.coursesapi.exceptions.ValidationException;
import com.anaclaraaraujo.coursesapi.modules.courses.CourseRepository;
import com.anaclaraaraujo.coursesapi.modules.courses.dto.CreateCourseRequest;
import com.anaclaraaraujo.coursesapi.modules.courses.entities.CourseEntity;
import com.anaclaraaraujo.coursesapi.modules.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public String execute(CreateCourseRequest createCourseRequest) throws Exception {
        if (!userRepository.existsById(createCourseRequest.getInstructorId())) {
            throw new Exception("Professor(a) não encontrado");
        }

        if (createCourseRequest.getTitle() == null || createCourseRequest.getTitle().isEmpty() ||
                createCourseRequest.getDescription() == null || createCourseRequest.getDescription().isEmpty()) {
            throw new ValidationException("Título e descrição são obrigatórios");
        }

        CourseEntity courseEntity = CourseEntity.builder()
                .title(createCourseRequest.getTitle())
                .description(createCourseRequest.getDescription())
                .instructorId(createCourseRequest.getInstructorId())
                .category(createCourseRequest.getCategory())
                .active(true)
                .build();

        courseEntity = this.courseRepository.save(courseEntity);

        return courseEntity.getId().toString();
    }
}
