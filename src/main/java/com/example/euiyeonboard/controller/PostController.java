package com.example.euiyeonboard.controller;

import com.example.euiyeonboard.dto.PostCreateRequest;
import com.example.euiyeonboard.dto.PostResponse;
import com.example.euiyeonboard.dto.PostUpdateRequest;
import com.example.euiyeonboard.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class PostController {
    private final PostService postService;

    // 게시글 작성
    @PostMapping("/posts")
    // @ResponseBody - > @RestController로 교체
        // @RestController = @Controller + @ResponseBody
    public ResponseEntity<PostResponse> savePost(@RequestBody @Valid PostCreateRequest postCreateRequest){
        return postService.savePost(postCreateRequest);
    }

    // 게시글 전체 조회 - 페이지네이션⭕, 정렬⭕
    @GetMapping("/posts1")
    public PagedModel<PostResponse> getAllPosts(@RequestParam(name = "page", defaultValue = "1") int page,
                                                @RequestParam(name = "size", defaultValue = "15") int size) {
        if(size > 100){ size = 100; }
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("createdAt").descending());
        return postService.getAllPosts(pageable);
    }

    // 게시글 전체 조회 - 페이지네이션❌, 정렬⭕
    @GetMapping("/posts2")
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return postService.getAllPosts();
    }

    // 게시글 전체 조회 - 검색키워드가 포함된 게시글 조회
    // board/post/search?keyword="의연 최고"
    @GetMapping("/posts/search")
    public PagedModel<PostResponse> getPostByKeyword(@RequestParam String keyword,
                                                     @RequestParam(name = "page", defaultValue = "1") int page,
                                                     @RequestParam(name = "size", defaultValue = "15") int size){
        if(size > 100){ size = 100; }
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("createdAt").descending());
        return postService.getPostByKeyword(keyword, pageable);
    }

    // 게시글 전체 조회 - 검색키워드가 포함된 제목의 게시글 조회
    // board/post/search/title?keyword="의연 최고"
    @GetMapping("/posts/search/title")
    public PagedModel<PostResponse> getPostByTitle(@RequestParam String keyword,
                                                   @RequestParam(name = "page", defaultValue = "1") int page,
                                                   @RequestParam(name = "size", defaultValue = "15") int size){
        if(size > 100){ size = 100; }
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("createdAt").descending());
        return postService.getPostByTitle(keyword, pageable);
    }

    // 특정 게시글 조회 - id로 단건 조회
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable("postId") Long postId){
        return postService.getPost(postId);
    }
    

    // 게시글 수정
    @PatchMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable("postId") Long postId,
                                                   @RequestBody @Valid PostUpdateRequest postUpdateRequest){
        return postService.updatePost(postId, postUpdateRequest);
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") Long postId){
        return postService.deletePost(postId);
    }
}
