package com.example.euiyeonboard.controller;

import com.example.euiyeonboard.dto.PostCreateRequest;
import com.example.euiyeonboard.dto.PostResponse;
import com.example.euiyeonboard.dto.PostUpdateRequest;
import com.example.euiyeonboard.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        // @RestController = @Controller + @ResponseBody
    public ResponseEntity<PostResponse> savePost(@RequestBody @Valid PostCreateRequest postCreateRequest){
        return postService.savePost(postCreateRequest);
    }

    // 게시글 전체 조회
    @GetMapping("/post")
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return postService.getAllPosts();
    }

    // 특정 게시글 조회
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable("postId") Long postId){
        return postService.getPost(postId);
    }

    // 게시글 수정
    @PatchMapping("/post/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable("postId") Long postId,
                                                   @RequestBody @Valid PostUpdateRequest postUpdateRequest){
        return postService.updatePost(postId, postUpdateRequest);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") Long postId){
        return postService.deletePost(postId);
    }
}
