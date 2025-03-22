package com.anaclaraaraujo.coursesapi.modules.auth;

import com.anaclaraaraujo.coursesapi.modules.auth.dto.CreateAuthRequest;
import com.anaclaraaraujo.coursesapi.modules.auth.useCases.CreateAuthUseCase;

import com.anaclaraaraujo.coursesapi.modules.users.entities.RoleUser;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CreateAuthUseCase createAuthUseCase;

    public AuthController(CreateAuthUseCase createAuthUseCase) {
        this.createAuthUseCase = createAuthUseCase;
    }

    @PostMapping("/instructor")
    public ResponseEntity<?>authInstructor(@RequestBody CreateAuthRequest createAuthRequest) {
        try {
            var token = this.createAuthUseCase.execute(createAuthRequest, RoleUser.TEACHER);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/student")
    public ResponseEntity<?>authStudent(@RequestBody CreateAuthRequest createAuthRequest) {
        try {
            var token = this.createAuthUseCase.execute(createAuthRequest, RoleUser.STUDENT);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
