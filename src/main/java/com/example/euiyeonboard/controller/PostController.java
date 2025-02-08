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
        // Level3. 게시글을 조회할 때 생성시간의 값도 포함되어야 한다.
        // Level3. 최근에 작성된 순으로 게시글이 조회되어야 한다.
        // Level3. 데이터 조회 개수는 최대 100개까지만 할 수 있어야 한다.
    @GetMapping("/post")
    public PagedModel<PostResponse> getAllPosts(@RequestParam(name = "page", defaultValue = "1") int page,
                                                      @RequestParam(name = "size", defaultValue = "15") int size) {
        if(size > 100){ size = 100; }
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("createdAt").descending());
        return postService.getAllPosts(pageable);
    }

    // 게시글 전체 조회 - 검색키워드가 포함된 게시글 조회
    // board/post/search?keyword="의연 최고"
    @GetMapping("/post/search")
    public PagedModel<PostResponse> getPostByKeyword(@RequestParam String keyword,
                                                     @RequestParam(name = "page", defaultValue = "1") int page,
                                                     @RequestParam(name = "size", defaultValue = "15") int size){
        if(size > 100){ size = 100; }
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("createdAt").descending());
        return postService.getPostByKeyword(keyword, pageable);
    }

    // 게시글 전체 조회 - 검색키워드가 포함된 제목의 게시글 조회
    // board/post/search/title?keyword="의연 최고"
    @GetMapping("/post/search/title")
    public PagedModel<PostResponse> getPostByTitle(@RequestParam String keyword,
                                                   @RequestParam(name = "page", defaultValue = "1") int page,
                                                   @RequestParam(name = "size", defaultValue = "15") int size){
        if(size > 100){ size = 100; }
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("createdAt").descending());
        return postService.getPostByTitle(keyword, pageable);
    }

    // 특정 게시글 조회 - id로 단건 조회
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
