package com.example.carrotMarket.service;

import com.example.carrotMarket.entity.member.Member;

public interface LikeService {

    void likePost(Long id);

    void cancelLike(Long id, Member member);
}
