package com.example.carrotMarket.repository;

import com.example.carrotMarket.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
