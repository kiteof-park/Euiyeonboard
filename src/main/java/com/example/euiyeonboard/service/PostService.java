package com.example.euiyeonboard.service;

import com.example.euiyeonboard.domain.Post;
import com.example.euiyeonboard.dto.PostCreateRequest;
import com.example.euiyeonboard.dto.PostResponse;
import com.example.euiyeonboard.dto.PostUpdateRequest;
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
    public PostResponse savePost(PostCreateRequest postCreateRequest){
//        Post post = Post.builder()
//                .title(postRequest.title())
//                .content(postRequest.content())
//                .build();
        Post post = new Post(postCreateRequest);

        Post savedPost = postRepository.save(post);

//        return PostResponse.builder()
//                .id(savedPost.getId())
//                .title(savedPost.getTitle())
//                .content(savedPost.getContent())
//                .build();
        return new PostResponse(savedPost);
    }

    // 게시글 전체 조회
    public List<PostResponse> getAllPosts(){
        List<Post> posts = postRepository.findAll();

        // ToRefactor : stream() 사용해서 코드 리팩토링 가능하지 않나?
        List<PostResponse> postResponses =  new ArrayList<>();

        for(Post post : posts){
            postResponses.add(new PostResponse(post));
        }
        return postResponses;
    }

    // 특정 게시글 조회
    public PostResponse getPost(Long id){
        // ToRefactor : Optional 메서드 사용해보기, get() 사용 지양하기!
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다!"));

        return new PostResponse(post);
    }
    
    // 게시글 수정
    public PostResponse updatePost(Long id, PostUpdateRequest postUpdateRequest){
        // 해당 id의 게시글이 존재하는지 확인 먼저할 것
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다!"));
        
        // 게시글 수정
        post.updatePost(postUpdateRequest);
        Post updatedPost =  postRepository.save(post);
        return new PostResponse(updatedPost);
    }
}
