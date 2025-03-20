package com.anaclaraaraujo.coursesapi.modules.users;

import com.anaclaraaraujo.coursesapi.modules.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
