package com.example.euiyeonboard.service;

import com.example.euiyeonboard.domain.Post;
import com.example.euiyeonboard.dto.PostRequest;
import com.example.euiyeonboard.dto.PostResponse;
import com.example.euiyeonboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor        // 필드 주입X -> 생성자 주입(@Autowired) -> @RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 게시글 작성
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

    // 게시글 전체 조회
    public List<PostResponse> getAllPosts(){
        List<Post> posts = postRepository.findAll();

        // ToRefactor : stream() 사용해서 코드 리팩토링 가능하지 않나?
        List<PostResponse> postResponses =  new ArrayList<>();

        for(Post post : posts){
            postResponses.add(PostResponse.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .build());
        }
        return postResponses;
    }

    // 특정 게시글 조회
    public PostResponse getPost(Long id){
        // ToRefactor : Optional 메서드 사용해보기, get() 사용 지양하기!
        Optional<Post> post = postRepository.findById(id);
        return PostResponse.builder()
                .id(post.get().getId())
                .title(post.get().getTitle())
                .content(post.get().getContent())
                .build();
    }

}
