package com.example.carrotMarket.service;

import com.example.carrotMarket.dto.CommentDto;
import com.example.carrotMarket.entity.comment.Comment;
import com.example.carrotMarket.entity.post.Post;

public interface CommentService {

    Long createComment(Long postId, CommentDto commentDto);

    void updateComment(Long commentId, CommentDto commentDto);

    void deleteComment(Long commentId);

    default Comment dtoToEntity(CommentDto commentDto, Post post) {
        return Comment.builder()
                .content(commentDto.getContent())
                .post(post)
                .build();
    }
}
