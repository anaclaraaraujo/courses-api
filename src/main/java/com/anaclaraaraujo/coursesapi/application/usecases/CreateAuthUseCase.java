package com.anaclaraaraujo.coursesapi.application.usecases;

import com.anaclaraaraujo.coursesapi.application.dto.CreateAuthRequest;
import com.anaclaraaraujo.coursesapi.application.dto.CreateAuthResponse;
import com.anaclaraaraujo.coursesapi.infrastructure.persistence.repositories.UserRepository;
import com.anaclaraaraujo.coursesapi.domain.enums.RoleUser;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

@Service
public class CreateAuthUseCase {
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CreateAuthResponse execute(CreateAuthRequest createAuthRequest, RoleUser roleUser) {
        var user = this.userRepository.findByEmail(createAuthRequest.getEmail());

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Email or password is incorrect");
        }

        if (!user.get().getRole().equals(roleUser)) {
            throw new UsernameNotFoundException("Email or password is incorrect");
        }

        var passwordMatches = this.passwordEncoder.matches(createAuthRequest.getPassword(), user.get().getPassword());

        if (!passwordMatches) {
            throw new UsernameNotFoundException("Password is incorrect");
        }

        var issuedAt = Instant.now();
        var expiresIn = issuedAt.plus(Duration.ofMinutes(10));

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        Map<String, String> claims = new HashMap<>();
        claims.put("roles", roleUser.toString());

        String token = Jwts.builder()
                .subject(user.get().getId().toString())
                .issuer("courses")
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiresIn))
                .claims(claims)
                .signWith(key)
                .compact();

        return CreateAuthResponse.builder()
                .token(token)
                .created_at(Date.from(issuedAt))
                .expires_in(Date.from(expiresIn))
                .build();
    }

}
