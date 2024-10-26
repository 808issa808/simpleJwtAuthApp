package com.example.jwtauthapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text")
    private String text;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @ManyToOne(fetch =FetchType.LAZY)
    @ToString.Exclude // Исключаем поле из метода toString для предотвращения рекурсии
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private User author;

}
