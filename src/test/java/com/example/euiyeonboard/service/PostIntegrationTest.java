package com.example.euiyeonboard.service;

import com.example.euiyeonboard.domain.Post;
import com.example.euiyeonboard.dto.PostRequest;
import com.example.euiyeonboard.dto.PostResponse;
import com.example.euiyeonboard.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostIntegrationTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("post 등록 테스트")
    void postTest(){
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
}