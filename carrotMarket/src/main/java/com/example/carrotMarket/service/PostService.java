package com.example.carrotMarket.service;

import com.example.carrotMarket.dto.PostDto;
import com.example.carrotMarket.dto.PostRequestDto;
import com.example.carrotMarket.dto.PostResponseDto;
import com.example.carrotMarket.dto.SliceResponse;
import com.example.carrotMarket.entity.post.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

public interface PostService {
    SliceResponse<PostDto> getPosts(Pageable pageable);

    Long createPost(PostRequestDto postRequestDto, List<MultipartFile> multipartFiles);

    PostResponseDto getPost(Long id);

    void updatePost(Long id, PostRequestDto postRequestDto, List<MultipartFile> multipartFiles);

    void deletePost(Long id);

    default Post requestDtoToEntity(PostRequestDto postRequestDto) {
        return Post.builder()
                .title(postRequestDto.getTitle())
                .contents(postRequestDto.getContent())
                .price(postRequestDto.getPrice())
                .status(postRequestDto.getStatus())
                .build();

    }

    default PostResponseDto entityToResponseDto(Post post) {
        List<String> imageUrls = post.getImages().stream()
                .map(img -> ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/images/")
                        .path(img.getStoreFileName())
                        .toUriString())
                .collect(Collectors.toList());

        return PostResponseDto.builder()
                .title(post.getTitle())
                .contents(post.getContents())
                .price(post.getPrice())
                .status(post.getStatus())
                .createdAt(post.getCreatedAt())
                .lastModifiedAt(post.getLastModifiedAt())
                .imageUrls(imageUrls)
                .build();
    }

    default PostDto entityToDto(Post post) {
        String storeFileName = post.getImages().getFirst().getStoreFileName();
        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/images/")
                .path(storeFileName)
                .toUriString();

        return PostDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .price(post.getPrice())
//                .address(post.getMember().getAddress())
                .createdAt(post.getCreatedAt())
                .lastModifiedAt(post.getLastModifiedAt())
                .thumbnail(imageUrl)
                .build();

    }
}
