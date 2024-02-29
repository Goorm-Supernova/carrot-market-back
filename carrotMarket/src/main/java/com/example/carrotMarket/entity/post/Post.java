package com.example.carrotMarket.entity.post;

import com.example.carrotMarket.entity.member.Member;
import com.example.carrotMarket.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;

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

    public void setMember(Member member) {
        this.member = member;
    }
}
