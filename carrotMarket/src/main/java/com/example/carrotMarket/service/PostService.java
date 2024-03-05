package com.example.carrotMarket.service;

import com.example.carrotMarket.dto.PostCreateDto;
import com.example.carrotMarket.dto.PostResDto;
import com.example.carrotMarket.entity.post.Post;
import com.example.carrotMarket.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public interface PostService {
    Slice<Post> getPosts(Pageable pageable);

    Long createPost(PostCreateDto postCreateDto, List<MultipartFile> multipartFiles) throws IOException;

    PostResDto getPost(Long id);

    default Post createDtoToEntity(PostCreateDto postCreateDto) {
        return Post.builder()
                .title(postCreateDto.getTitle())
                .contents(postCreateDto.getContent())
                .price(postCreateDto.getPrice())
                .status(Status.SALE)
                .build();

    }

    default PostResDto entityToResponseDto(Post post) {
        List<String> imageUrls = post.getImages().stream()
                .map(img -> ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/images/")
                        .path(img.getStoreFileName())
                        .toUriString())
                .collect(Collectors.toList());

        return PostResDto.builder()
                .title(post.getTitle())
                .contents(post.getContents())
                .price(post.getPrice())
                .status(post.getStatus())
                .createdAt(post.getCreatedAt())
                .lastModifiedAt(post.getLastModifiedAt())
                .imageUrls(imageUrls)
                .build();
    }
}
