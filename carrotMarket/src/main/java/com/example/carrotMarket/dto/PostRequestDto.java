package com.example.carrotMarket.dto;

import com.example.carrotMarket.enums.Status;
import lombok.Data;

@Data
public class PostRequestDto {
    private String title;
    private String content;
    private int price;
    private Status status = Status.SALE;
}
