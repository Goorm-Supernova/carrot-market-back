package com.example.carrotMarket.controller;

import com.example.carrotMarket.entity.post.Post;
import com.example.carrotMarket.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public Slice<Post> getPosts(Pageable pageable) {
        return postService.getPosts(pageable);
    }
}
