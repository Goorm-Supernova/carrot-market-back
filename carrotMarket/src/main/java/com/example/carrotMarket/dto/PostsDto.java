package com.example.carrotMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostsDto {
    private Long postId;
    private String title;
    private String content;
    private int likeCount;
    private int price;
    private String address;
    // TODO: 2/29/24 게시물 업로드 구현 후 thumbnail, create_date, update_date 추가
    // TODO: 2/29/24 채팅 기능 구현 후 chatCnt 추가
}

