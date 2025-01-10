package com.example.euiyeonboard.service;

import com.example.euiyeonboard.domain.Post;
import com.example.euiyeonboard.dto.PostRequest;
import com.example.euiyeonboard.dto.PostResponse;
import com.example.euiyeonboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor        // 필드 주입X -> 생성자 주입(@Autowired) -> @RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponse savePost(PostRequest postRequest){
        Post post = Post.builder()
                .title(postRequest.title())
                .content(postRequest.content())
                .build();

        Post savedPost = postRepository.save(post);

        return PostResponse.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .build();
    }
}
