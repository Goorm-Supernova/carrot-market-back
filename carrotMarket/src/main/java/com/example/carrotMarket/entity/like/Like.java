package com.example.carrotMarket.entity.like;

import com.example.carrotMarket.entity.member.Member;
import com.example.carrotMarket.entity.post.Post;
import com.querydsl.core.Fetchable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }
}
