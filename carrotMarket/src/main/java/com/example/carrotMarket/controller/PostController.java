package com.example.carrotMarket.controller;

import com.example.carrotMarket.dto.PostCreateDto;
import com.example.carrotMarket.entity.post.Post;
import com.example.carrotMarket.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public Slice<Post> getPosts(Pageable pageable) {
        return postService.getPosts(pageable);
    }

    @PostMapping
    public ResponseEntity<?> createPost(
            @RequestPart("post") PostCreateDto postCreateDto,
            @RequestPart("images") List<MultipartFile> images) throws IOException {

        Long postId = postService.createPost(postCreateDto, images);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(postId)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
