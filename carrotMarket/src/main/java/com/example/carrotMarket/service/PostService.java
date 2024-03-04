package com.example.carrotMarket.service;

import com.example.carrotMarket.dto.PostCreateDto;
import com.example.carrotMarket.entity.post.Post;
import com.example.carrotMarket.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    Slice<Post> getPosts(Pageable pageable);

    Long createPost(PostCreateDto postCreateDto, List<MultipartFile> multipartFiles) throws IOException;



    default Post createDtoToEntity(PostCreateDto postCreateDto) {
        return Post.builder()
                .title(postCreateDto.getTitle())
                .contents(postCreateDto.getContent())
                .price(postCreateDto.getPrice())
                .status(Status.SALE)
                .build();

    }
}
