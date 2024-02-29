package com.example.carrotMarket.entity.like;

import com.example.carrotMarket.entity.member.Member;
import com.example.carrotMarket.entity.post.Post;
import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Like {
    @Id@GeneratedValue
    private Long id;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Post post;
}
