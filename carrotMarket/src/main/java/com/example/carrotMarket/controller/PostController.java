package com.example.carrotMarket.controller;

import com.example.carrotMarket.dto.PostDto;
import com.example.carrotMarket.dto.PostRequestDto;
import com.example.carrotMarket.dto.PostResponseDto;
import com.example.carrotMarket.dto.SliceResponse;
import com.example.carrotMarket.entity.post.Post;
import com.example.carrotMarket.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public SliceResponse<PostDto> getPosts(
            @PageableDefault(size = 12, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return postService.getPosts(pageable);

    }

    @PostMapping
    public ResponseEntity<?> createPost(
            @RequestPart("post") PostRequestDto postRequestDto,
            @RequestPart("images") List<MultipartFile> images) {

        // 현재 인증된 사용자의 정보 가져와서 하는 로직 필요 (예)
//        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        // Username을 기반으로 Member 엔티티 조회
//        Member member = memberRepository.findByUsername(currentUsername);
//
//        // DTO를 엔티티로 변환 및 Member 설정
//        Post post = convertToEntity(postCreateDto);
//        post.setMember(member); // 게시물에 회원 정보 설정

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);

        return ResponseEntity.noContent().build();
    }
}
