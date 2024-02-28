package com.example.carrotMarket.entity.img;

import com.example.carrotMarket.entity.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Img {
    @Id@GeneratedValue
    private Long id;
    @ManyToOne
    private Post post;
    private String src;
}
