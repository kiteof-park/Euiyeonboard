package com.example.euiyeonboard.controller;

import com.example.euiyeonboard.domain.Post;
import com.example.euiyeonboard.dto.PostRequest;
import com.example.euiyeonboard.dto.PostResponse;
import com.example.euiyeonboard.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class PostController {
    private final PostService postService;

    // 게시글 작성
    @PostMapping("/post")
    // @ResponseBody - > @RestController로 교체
    public PostResponse savePost(@RequestBody @Valid PostRequest postRequest){
        return postService.savePost(postRequest);
    }

    // 게시글 전체 조회
    @GetMapping("/post")
    public List<PostResponse> getAllPosts(){
        return postService.getAllPosts();
    }

    // 특정 게시글 조회
    @GetMapping("/post/{postId}")
    public PostResponse getPost(Long id){
        return postService.getPost(id);
    }

}
