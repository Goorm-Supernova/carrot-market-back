package com.example.carrotMarket.service;

import com.example.carrotMarket.dto.PostCreateDto;
import com.example.carrotMarket.entity.img.Img;
import com.example.carrotMarket.entity.post.Post;
import com.example.carrotMarket.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Value("${file.dir}")
    private String fileDir;

    @Override
    public Slice<Post> getPosts(Pageable pageable) {
        return postRepository.findAllBy(pageable);
    }

    @Override
    public Long createPost(PostCreateDto postCreateDto, List<MultipartFile> multipartFiles) throws IOException {
        List<Img> storeImageFiles = storeFiles(multipartFiles);
        Post post = createDtoToEntity(postCreateDto);
        for (Img storeImageFile : storeImageFiles) {
            post.addImage(storeImageFile);
        }
        return postRepository.save(post).getId();
    }

    private String getFullPath(String filename) {
        return fileDir + filename;
    }

    private List<Img> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<Img> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    private Img storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new Img(originalFileName, storeFileName);
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
