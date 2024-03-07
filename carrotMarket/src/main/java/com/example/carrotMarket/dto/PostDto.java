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
    private int likeCnt;
    private String thumbnail;
}

