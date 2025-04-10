package com.anaclaraaraujo.coursesapi.interfaces.controllers;

import com.anaclaraaraujo.coursesapi.interfaces.exception.ValidationException;
import com.anaclaraaraujo.coursesapi.application.dto.CreateUserRequest;
import com.anaclaraaraujo.coursesapi.application.usecases.CreateUserUseCase;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateUserRequest createUser) {
        try {
            String result = this.createUserUseCase.execute(createUser);
            return ResponseEntity.ok(result);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}