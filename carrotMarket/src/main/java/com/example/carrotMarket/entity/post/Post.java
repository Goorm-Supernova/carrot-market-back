package com.example.carrotMarket.entity.post;

import com.example.carrotMarket.entity.member.Member;
import com.example.carrotMarket.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {
    @Id@GeneratedValue
    private Long id;
    @ManyToOne
    private Member member;
    private String title;
    private String contents;
    private Status status;
}
