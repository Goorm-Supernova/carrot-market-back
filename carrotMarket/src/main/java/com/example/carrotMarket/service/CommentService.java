package com.example.carrotMarket.service;

import com.example.carrotMarket.dto.CommentRequestDto;
import com.example.carrotMarket.entity.comment.Comment;
import com.example.carrotMarket.entity.post.Post;

public interface CommentService {

    Long createComment(Long postId, CommentRequestDto commentRequestDto);

    void updateComment(Long commentId, CommentRequestDto commentRequestDto);

    void deleteComment(Long commentId);

    default Comment dtoToEntity(CommentRequestDto commentRequestDto, Post post) {
        return Comment.builder()
                .content(commentRequestDto.getContent())
                .post(post)
                .build();
    }
}
