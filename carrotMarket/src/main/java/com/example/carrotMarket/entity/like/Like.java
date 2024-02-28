package com.example.carrotMarket.entity.like;

import com.example.carrotMarket.entity.member.Member;
import com.example.carrotMarket.entity.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Like {
    @Id@GeneratedValue
    private Long id;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Post post;
}
