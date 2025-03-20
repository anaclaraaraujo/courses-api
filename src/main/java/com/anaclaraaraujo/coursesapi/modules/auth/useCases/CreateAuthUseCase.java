package com.anaclaraaraujo.coursesapi.modules.auth.useCases;

import com.anaclaraaraujo.coursesapi.modules.auth.dto.CreateAuthRequest;
import com.anaclaraaraujo.coursesapi.modules.users.UserRepository;
import com.anaclaraaraujo.coursesapi.modules.users.entities.RoleUser;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

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

    public String execute(CreateAuthRequest createAuthRequest) {
        var user = this.userRepository.findByEmail(createAuthRequest.getEmail());

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Email or password is incorrect");
        }

        if (!user.get().getRole().equals(RoleUser.TEACHER)) {
            throw new UsernameNotFoundException("Email or password is incorrect");
        }

        var passwordMatches = this.passwordEncoder.matches(createAuthRequest.getPassword(), user.get().getPassword());

        if (!passwordMatches) {
            throw new UsernameNotFoundException("Password is incorrect");
        }

        var issuedAt = Instant.now();
        var expiresIn = issuedAt.plus(Duration.ofMinutes(10));

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        String token = Jwts.builder()
                .subject(user.get().getId().toString())
                .issuer("courses")
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiresIn))
                .signWith(key)
                .compact();
        return token;
    }

}
