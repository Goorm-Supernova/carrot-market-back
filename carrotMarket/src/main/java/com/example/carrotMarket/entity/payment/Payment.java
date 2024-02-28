package com.example.carrotMarket.entity.payment;

import com.example.carrotMarket.entity.member.Member;
import com.example.carrotMarket.entity.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Payment {
    @Id@GeneratedValue
    private Long id;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Post post;
    private boolean isBuyer;
}
