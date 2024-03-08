package com.example.carrotMarket.service;

import com.example.carrotMarket.dto.*;
import com.example.carrotMarket.entity.img.Img;
import com.example.carrotMarket.entity.post.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

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
                .content(postRequestDto.getContent())
                .price(postRequestDto.getPrice())
                .status(postRequestDto.getStatus())
                .build();

    }

    default PostResponseDto entityToResponseDto(Post post, int likeCnt) {
        List<String> imageUrls = post.getImages().stream()
                .map(Img::getFilePath)
                .collect(Collectors.toList());

        List<CommentResponseDto> comments = post.getComments().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());

        return PostResponseDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .price(post.getPrice())
                .status(post.getStatus())
//                .memberId(post.getMember().getId())
//                .nickName(post.getMember().getNickName())
//                .address(post.getMember().getAddress())
                .createdAt(post.getCreatedAt())
                .lastModifiedAt(post.getLastModifiedAt())
                .imageUrls(imageUrls)
                .comments(comments)
                .likeCnt(likeCnt)
                .build();
    }

    default PostDto entityToDto(Post post) {

        String imageUrl = post.getImages().get(0).getFilePath();

        return PostDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .price(post.getPrice())
//                .address(post.getMember().getAddress())
                .createdAt(post.getCreatedAt())
                .lastModifiedAt(post.getLastModifiedAt())
                .thumbnail(imageUrl)
                .likeCnt(post.getLikes().size())
                .build();

    }
}
