package com.example.carrotMarket.entity.post;

import com.example.carrotMarket.entity.BaseEntity;
import com.example.carrotMarket.entity.comment.Comment;
import com.example.carrotMarket.entity.img.Img;
import com.example.carrotMarket.entity.like.Like;
import com.example.carrotMarket.entity.member.Member;
import com.example.carrotMarket.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String title;
    private String content;
    private int price;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Img> images = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void addImage(Img image) {
        images.add(image);
        image.setPost(this);
    }

    public void addLike(Like like) {
        likes.add(like);
        like.setPost(this);
    }

    public void update(String title, String contents, int price, Status status, List<Img> images) {
        this.title = title;
        this.content = contents;
        this.price = price;
        this.status = status;

        this.images.clear();
        for (Img image : images) {
            this.addImage(image);
        }

    }
}
