package com.anaclaraaraujo.coursesapi.modules.users.controllers;

import com.anaclaraaraujo.coursesapi.modules.users.UserRepository;
import com.anaclaraaraujo.coursesapi.modules.users.entities.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public UserEntity create(@Valid @RequestBody UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }
}