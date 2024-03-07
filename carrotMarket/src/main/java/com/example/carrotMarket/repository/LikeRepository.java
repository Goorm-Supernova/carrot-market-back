package com.example.carrotMarket.repository;

import com.example.carrotMarket.entity.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("select count(l) from Like l where l.post.id = :postId")
    Long countLikesByPostId(@Param("postId") Long postId);
}
