package com.example.carrotMarket.service;

import com.example.carrotMarket.entity.like.Like;
import com.example.carrotMarket.entity.member.Member;
import com.example.carrotMarket.entity.post.Post;
import com.example.carrotMarket.repository.LikeRepository;
import com.example.carrotMarket.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public void likePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        Like like = new Like();
        post.addLike(like);
    }

    @Override
    public void cancelLike(Long postId, Member member) {

    }
}
