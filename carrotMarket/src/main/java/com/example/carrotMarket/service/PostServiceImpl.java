package com.example.carrotMarket.service;

import com.example.carrotMarket.dto.PostDto;
import com.example.carrotMarket.dto.PostRequestDto;
import com.example.carrotMarket.dto.PostResponseDto;
import com.example.carrotMarket.dto.SliceResponse;
import com.example.carrotMarket.entity.img.Img;
import com.example.carrotMarket.entity.post.Post;
import com.example.carrotMarket.repository.LikeRepository;
import com.example.carrotMarket.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final ImageFileService imageUploader;

    @Value("${file.dir}")
    private String fileDir;

    @Override
    public SliceResponse<PostDto> getPosts(Pageable pageable) {
        Slice<Post> slice = postRepository.findAllBy(pageable);
        List<PostDto> postDtoList = slice.getContent().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
        return new SliceResponse<>(postDtoList, slice.hasNext());
    }

    @Override
    @Transactional
    public Long createPost(PostRequestDto postRequestDto, List<MultipartFile> multipartFiles) {
        List<Img> storeImageFiles = storeFiles(multipartFiles);
        Post post = requestDtoToEntity(postRequestDto);
        for (Img storeImageFile : storeImageFiles) {
            post.addImage(storeImageFile);
        }
        return postRepository.save(post).getId();
    }

    @Override
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findPostWithComment(id).orElseThrow();
        int likeCnt = likeRepository.countLikesByPostId(id).intValue();
        return entityToResponseDto(post, likeCnt);
    }

    @Override
    @Transactional
    public void updatePost(Long id, PostRequestDto postRequestDto, List<MultipartFile> multipartFiles) {
        Post post = postRepository.findById(id).orElseThrow();
        deleteFiles(post);
        List<Img> images = storeFiles(multipartFiles);
        post.update(postRequestDto.getTitle(), postRequestDto.getContent(), postRequestDto.getPrice(), postRequestDto.getStatus(), images);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        deleteFiles(post);
        postRepository.deleteById(id);
    }

    private void deleteFiles(Post post) {
        for (Img image : post.getImages()) {
            String fullPath = getFullPath(image.getStoreFileName());
            Path filePath = Paths.get(fullPath);
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private String getFullPath(String filename) {
        return fileDir + filename;
    }

    private List<Img> storeFiles(List<MultipartFile> multipartFiles) {
        List<Img> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    private Img storeFile(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);
        String filePath = imageUploader.uploadImage(multipartFile, storeFileName);
        return new Img(originalFileName, storeFileName, filePath);
    }

    private String createStoreFileName(String originalFileName) {
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }
}
