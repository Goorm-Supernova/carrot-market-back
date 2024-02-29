package com.example.carrotMarket.entity.img;

import com.example.carrotMarket.entity.post.Post;
import jakarta.persistence.*;

@Entity
public class Img {
    @Id@GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    private String src;

    public void setPost(Post post) {
        this.post = post;
    }
}
