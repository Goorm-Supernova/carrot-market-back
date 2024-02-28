package com.example.carrotMarket.entity.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
