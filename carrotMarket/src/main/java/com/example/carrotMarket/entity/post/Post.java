package com.example.carrotMarket.entity.post;

import com.example.carrotMarket.entity.img.Img;
import com.example.carrotMarket.entity.member.Member;
import com.example.carrotMarket.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Post {
    @Id@GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String title;
    private String contents;
    private Status status;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Img> images = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void addImage(Img image) {
        images.add(image);
        image.setPost(this);
    }
}
