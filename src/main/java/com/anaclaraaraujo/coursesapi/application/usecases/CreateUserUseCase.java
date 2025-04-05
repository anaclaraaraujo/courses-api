package com.anaclaraaraujo.coursesapi.application.usecases;

import com.anaclaraaraujo.coursesapi.interfaces.exception.ValidationException;
import com.anaclaraaraujo.coursesapi.infrastructure.persistence.repositories.UserRepository;
import com.anaclaraaraujo.coursesapi.application.dto.CreateUserRequest;
import com.anaclaraaraujo.coursesapi.domain.enums.RoleUser;
import com.anaclaraaraujo.coursesapi.domain.entities.UserEntity;
import com.anaclaraaraujo.coursesapi.domain.services.EmailValidator;
import com.anaclaraaraujo.coursesapi.domain.services.PasswordValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String execute(CreateUserRequest user) throws ValidationException {
        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
            throw new ValidationException("Preencher campos obrigatórios!");
        }

        if(!EmailValidator.isValid(user.getEmail())) {
                throw new ValidationException("Email inválido!");
        }

        if(!PasswordValidator.isValidPassword(user.getPassword())) {
            throw new ValidationException("Senha não atende aos critérios!");
        }

        Optional<UserEntity> userEntity = this.userRepository.findByEmail(user.getEmail());

        if (userEntity.isPresent()) {
            throw new RuntimeException("E-mail já cadastrado!");
        }

        var passwordCrypt = passwordEncoder.encode(user.getPassword());

        UserEntity userToSave = UserEntity.builder()
                .email(user.getEmail())
                .password(passwordCrypt)
                .name(user.getName())
                .role(RoleUser.valueOf(user.getRole().toUpperCase())).build();

        userToSave = this.userRepository.save(userToSave);

        return "LINK " + userToSave.getId();
    }
}
