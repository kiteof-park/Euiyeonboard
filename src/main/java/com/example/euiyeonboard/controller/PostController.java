package com.example.euiyeonboard.controller;

import com.example.euiyeonboard.dto.PostRequest;
import com.example.euiyeonboard.dto.PostResponse;
import com.example.euiyeonboard.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    // @ResponseBody - > @RestController로 교체
    public PostResponse savePost(@RequestBody @Valid PostRequest postRequest){
        return postService.savePost(postRequest);
    }
}
