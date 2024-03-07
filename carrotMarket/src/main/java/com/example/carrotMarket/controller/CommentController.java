package com.example.carrotMarket.controller;

import com.example.carrotMarket.dto.CommentRequestDto;
import com.example.carrotMarket.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentRequestDto commentRequestDto) {

        Long commentId = commentService.createComment(postId, commentRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commentId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentRequestDto commentRequestDto) {

        commentService.updateComment(commentId, commentRequestDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity.noContent().build();
    }
}
