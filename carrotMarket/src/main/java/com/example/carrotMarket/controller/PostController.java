package com.example.carrotMarket.controller;

import com.example.carrotMarket.dto.PostRequestDto;
import com.example.carrotMarket.dto.PostResponseDto;
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
            @RequestPart("post") PostRequestDto postRequestDto,
            @RequestPart("images") List<MultipartFile> images) throws IOException {

        Long postId = postService.createPost(postRequestDto, images);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(postId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public PostResponseDto getPost(@PathVariable("id") Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(
            @PathVariable("id") Long id,
            @RequestPart("post") PostRequestDto postRequestDto,
            @RequestPart("images") List<MultipartFile> images) {

        postService.updatePost(id, postRequestDto, images);

        return ResponseEntity.noContent().build();
    }
}
