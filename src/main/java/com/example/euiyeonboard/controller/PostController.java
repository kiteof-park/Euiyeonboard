package com.example.euiyeonboard.controller;

import com.example.euiyeonboard.dto.PostCreateRequest;
import com.example.euiyeonboard.dto.PostResponse;
import com.example.euiyeonboard.dto.PostUpdateRequest;
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
    public PostResponse savePost(@RequestBody @Valid PostCreateRequest postCreateRequest){

        return postService.savePost(postCreateRequest);
    }

    // 게시글 전체 조회
    @GetMapping("/post")
    public List<PostResponse> getAllPosts(){

        return postService.getAllPosts();
    }

    // 특정 게시글 조회
    @GetMapping("/post/{postId}")
    public PostResponse getPost(@PathVariable("postId") Long postId){

        return postService.getPost(postId);
    }

    // 게시글 수정
    @PatchMapping("/post/{postId}")
    public PostResponse updatePost(@PathVariable("postId") Long postId,
                                   @RequestBody @Valid PostUpdateRequest postUpdateRequest){

        return postService.updatePost(postId, postUpdateRequest);
    }

}
