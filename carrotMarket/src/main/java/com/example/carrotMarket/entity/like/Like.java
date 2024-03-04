package com.example.carrotMarket.entity.like;

import com.example.carrotMarket.entity.member.Member;
import com.example.carrotMarket.entity.post.Post;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "likes")
@Getter
public class Like {
    @Id@GeneratedValue
    private Long id;
    @ManyToOne
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }
}
