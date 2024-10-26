package com.example.jwtauthapp.service;

import com.example.jwtauthapp.entity.Post;
import com.example.jwtauthapp.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public List<Post> findAllByUserId(Long id) {
        return postRepository.findAllByAuthorId(id);
    }
}
