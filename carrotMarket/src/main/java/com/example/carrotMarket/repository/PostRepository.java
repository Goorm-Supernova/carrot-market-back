package com.example.carrotMarket.repository;

import com.example.carrotMarket.entity.post.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @EntityGraph(attributePaths = "images")
    Slice<Post> findAllBy(Pageable pageable);

    @Query("select p from Post p left join fetch Comment c on p.id = :id order by c.createdAt desc")
    Optional<Post> findPostWithComment(@Param("id") Long id);
}
