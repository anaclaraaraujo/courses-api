package com.anaclaraaraujo.coursesapi.infrastructure.persistence.repositories;

import com.anaclaraaraujo.coursesapi.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
