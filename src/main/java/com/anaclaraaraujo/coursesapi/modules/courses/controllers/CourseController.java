package com.anaclaraaraujo.coursesapi.modules.courses.controllers;

import com.anaclaraaraujo.coursesapi.exceptions.ErrorMessageDTO;
import com.anaclaraaraujo.coursesapi.exceptions.ValidationException;
import com.anaclaraaraujo.coursesapi.modules.courses.dto.CreateCourseRequest;
import com.anaclaraaraujo.coursesapi.modules.courses.dto.UpdateCourseRequest;
import com.anaclaraaraujo.coursesapi.modules.courses.useCases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @Autowired
    private ListCourseUseCase listCourseUseCase;

    @Autowired
    private UpdateCourseUseCase updateCourseUseCase;

    @Autowired
    private UpdateCourseActiveUseCase updateCourseActiveUseCase;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateCourseRequest createCourseRequest) throws Exception {
        try {
            String result = this.createCourseUseCase.execute(createCourseRequest);
            return ResponseEntity.ok(result);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        try {
            return ResponseEntity.ok(listCourseUseCase.execute(name, category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UpdateCourseRequest updateCourseRequest) {
        try {
            String result = updateCourseUseCase.execute(id, updateCourseRequest);
            return ResponseEntity.ok(result);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(e.getMessage(), "id"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessageDTO(e.getMessage(), "id"));
        }
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<?> active(@PathVariable UUID id) {
        try {
            String result = updateCourseActiveUseCase.execute(id);
            return ResponseEntity.ok(result);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(e.getMessage(), "id"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessageDTO(e.getMessage(), "id"));
        }
    }
}
