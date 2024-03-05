package com.example.carrotMarket.service;

import com.example.carrotMarket.dto.CommentDto;
import com.example.carrotMarket.entity.comment.Comment;
import com.example.carrotMarket.entity.post.Post;
import com.example.carrotMarket.repository.CommentRepository;
import com.example.carrotMarket.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public Long createComment(Long postId, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow();

        Comment comment = dtoToEntity(commentDto, post);

        return commentRepository.save(comment).getId();

    }

    @Override
    @Transactional
    public void updateComment(Long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();

        comment.update(commentDto.getContent());
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
