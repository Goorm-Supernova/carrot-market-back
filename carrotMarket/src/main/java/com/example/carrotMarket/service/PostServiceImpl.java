package com.example.carrotMarket.service;

import com.example.carrotMarket.entity.post.Post;
import com.example.carrotMarket.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Slice<Post> getPosts(Pageable pageable) {
        return postRepository.findAllBy(pageable);
    }
}
