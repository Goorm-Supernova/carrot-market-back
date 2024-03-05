package com.example.carrotMarket.dto;

import com.example.carrotMarket.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private String title;
    private String content;
    private int price;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private List<String> imageUrls;
}