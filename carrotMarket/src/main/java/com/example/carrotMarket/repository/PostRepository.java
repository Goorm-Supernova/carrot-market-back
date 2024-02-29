package com.example.carrotMarket.repository;

import com.example.carrotMarket.entity.post.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Slice<Post> findAllBy(Pageable pageable);
}
