package com.example.carrotMarket.dto;

import lombok.Data;

@Data
public class PostCreateDto {
    private String title;
    private String content;
    private int price;
}
