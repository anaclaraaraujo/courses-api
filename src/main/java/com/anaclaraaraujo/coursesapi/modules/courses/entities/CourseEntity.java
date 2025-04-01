package com.anaclaraaraujo.coursesapi.modules.courses.entities;

import com.anaclaraaraujo.coursesapi.modules.users.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "courses")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private CourseCategory category;

    @Builder.Default
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "instructor_id", updatable = false, insertable = false)
    private UserEntity instructor;

    @Column(name = "instructor_id")
    private UUID instructorId;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
