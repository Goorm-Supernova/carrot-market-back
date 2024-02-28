package com.example.carrotMarket.entity.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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

}
