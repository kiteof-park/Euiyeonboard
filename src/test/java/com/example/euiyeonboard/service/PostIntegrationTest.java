package com.example.euiyeonboard.service;

import com.example.euiyeonboard.dto.PostRequest;
import com.example.euiyeonboard.dto.PostResponse;
import com.example.euiyeonboard.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostIntegrationTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    // 조회 기능 테스트를 위해 추가
    @BeforeEach
    void cleanRepository(){
        postRepository.deleteAll();
    }
    
    @Test
    @DisplayName("post 등록 테스트")
    void savePostTest(){
        // given
        PostRequest request = PostRequest.builder()
                .title("테스트 제목이에옹")
                .content("테스트 내용이에옹")
                .build();
        // when
        PostResponse response = postService.savePost(request);

        // then
        assertAll(
                () -> assertEquals("테스트 제목이에옹", response.title()),
                () -> assertEquals("테스트 내용이에옹", response.content())
        );
    }
    
    @Test
    @DisplayName("post 전체 조회 테스트")
    void getAllPostsTest(){
        // given
        PostRequest request1 = PostRequest.builder()
                .title("테스트 제목이다옹1")
                .content("테스트 내용이다옹1")
                .build();

        postService.savePost(request1);

        PostRequest request2 = PostRequest.builder()
                .title("테스트 제목이다옹2")
                .content("테스트 내용이다옹2")
                .build();

        postService.savePost(request2);

        // when
        List<PostResponse> posts = postService.getAllPosts();

        // then
        assertAll(
                () -> assertEquals(2, posts.size()),
                () -> assertThat(posts).extracting("title").containsExactly(
                        "테스트 제목이다옹1", "테스트 제목이다옹2"
                )
        );
    }

//    @Test
//    @DisplayName("post 단일 조회 테스트")
//    void getPostTest(){
//        // given
//        PostRequest request = PostRequest.builder()
//                .title("조회 테스트 제목이에옹")
//                .content("조회 테스트 내용이에옹")
//                .build();
//
//        PostResponse response = postService.savePost(request);
//
//        // when
//        PostResponse post = postService.getPost(response.id());
//
//        // then
//        assertAll(
//                // response와 post 비교하기
//                () ->
//        );
//    }

}