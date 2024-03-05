package com.example.carrotMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long postId;
    private String title;
    private int price;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private String thumbnail;
    // TODO: 2/29/24 좋아요, 채팅 기능 구현 후 likeCnt, chatCnt 추가
}

