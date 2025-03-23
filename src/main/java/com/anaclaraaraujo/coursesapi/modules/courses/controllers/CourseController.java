package com.anaclaraaraujo.coursesapi.modules.courses.controllers;

import com.anaclaraaraujo.coursesapi.exceptions.ValidationException;
import com.anaclaraaraujo.coursesapi.modules.courses.dto.CreateCourseRequest;
import com.anaclaraaraujo.coursesapi.modules.courses.useCases.CreateCourseUseCase;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

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

//    @GetMapping("/") // Deve ser possível listar todas os cursos salvos no banco de dados.
//    @DeleteMapping("/{id}") // Deve ser possível remover um curso pelo id.
//    @PutMapping("/{id}") // Deve ser possível atualizar um curso pelo id.
//    @PatchMapping("id/active") // servirá para marcar se o curso está ativo ou não

}
