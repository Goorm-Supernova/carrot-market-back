package com.example.carrotMarket.entity.member;

import com.example.carrotMarket.entity.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {
    @Id@GeneratedValue
    private Long id;
    private String name;
    private String nickName;
    private String phoneNum;
    private String address;
    private Integer latitude;
    private Integer longitude;
    private String loginId;
    private Integer kakaoId;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public Member(Long id, String name, String nickName, String phoneNum, String address, Integer latitude,
        Integer longitude, String loginId, Integer kakaoId) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.phoneNum = phoneNum;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.loginId = loginId;
        this.kakaoId = kakaoId;
    }

    public void updateProfile(String name, String nickName, String phoneNum, String address, Integer latitude, Integer longitude) {
        this.name = name;
        this.nickName = nickName;
        this.phoneNum = phoneNum;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void addPost(Post post) {
        this.posts.add(post);
        post.setMember(this);
    }
}
