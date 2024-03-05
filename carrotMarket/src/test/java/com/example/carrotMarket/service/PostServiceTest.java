package com.example.carrotMarket.service;

import com.example.carrotMarket.entity.img.Img;
import com.example.carrotMarket.entity.member.Member;
import com.example.carrotMarket.entity.post.Post;
import com.example.carrotMarket.enums.Status;
import com.example.carrotMarket.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 포스트_전체_조회() {
        for (int i = 0; i < 10; i++) {
            Member member = new Member((long) i, "member" + i, "nickName" + i, "phoneNum" + 1, "address" + 1, i, i, "loginId", i);
            Post post1 = Post.builder()
                    .title("title" + i)
                    .status(Status.SALE)
                    .contents("contents" + i)
                    .build();

            Post post2 = Post.builder()
                    .title("title" + i+11)
                    .status(Status.SALE)
                    .contents("contents" + i+11)
                    .build();

//            Img img1 = Img.builder().src("src" + i).build();
//            Img img2 = Img.builder().src("src" + i + 11).build();

//            post1.addImage(img1);
//            post2.addImage(img2);

            member.addPost(post1);
            member.addPost(post2);

            memberRepository.save(member);
        }

        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "id"));

//        Slice<Post> posts = postService.getPosts(pageable);

//        System.out.println("posts = " + posts);
//        System.out.println("posts.getContent() = " + posts.getContent());
//        System.out.println("posts.getPageable() = " + posts.getPageable());
//
//        assertThat(posts.getSize()).isEqualTo(5);
//        assertThat(posts.getContent()).isNotEmpty();
    }
}