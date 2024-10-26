package com.example.jwtauthapp.controller;

import com.example.jwtauthapp.entity.Post;
import com.example.jwtauthapp.security.CustomUserDetails;
import com.example.jwtauthapp.service.PostService;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/MyPosts")
    public ResponseEntity<List<Post>> getAllMyPosts(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId(); // Получаем ID пользователя
        System.out.println(userDetails.getUser());
        List<Post> posts = postService.findAllByUserId(userId); // Вызываем метод сервиса

        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build(); // Если постов нет, возвращаем 204 No Content
        }

        return ResponseEntity.ok(posts); // Возвращаем 200 OK с постами
    }

}
