package com.example.carrotMarket.dto;

import com.example.carrotMarket.entity.comment.Comment;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentResponseDto {
    // TODO: 3/6/24 멤버 서비스 구현 후 아래 정보 수정
//    private Long memberId;
//    private String nickName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public CommentResponseDto(Comment comment) {
        content = comment.getContent();
        createdAt = comment.getCreatedAt();
        lastModifiedAt = comment.getLastModifiedAt();
    }
}
