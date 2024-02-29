package com.example.carrotMarket.service;

import com.example.carrotMarket.entity.post.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostService {
    Slice<Post> getPosts(Pageable pageable);
}
