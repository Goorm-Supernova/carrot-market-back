package com.example.carrotMarket.entity.comment;

import com.example.carrotMarket.dto.CommentDto;
import com.example.carrotMarket.entity.member.Member;
import com.example.carrotMarket.entity.post.Post;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;

    public void setPost(Post post) {
        this.post = post;
    }

    public void update(String content) {
        this.content = content;
    }
}
