package com.example.euiyeonboard.service;

import com.example.euiyeonboard.domain.Post;
import com.example.euiyeonboard.dto.PostCreateRequest;
import com.example.euiyeonboard.dto.PostResponse;
import com.example.euiyeonboard.dto.PostUpdateRequest;
import com.example.euiyeonboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor        // 필드 주입X -> 생성자 주입(@Autowired) -> @RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 게시글 작성
    public ResponseEntity<PostResponse> savePost(PostCreateRequest postCreateRequest){
//        Post post = Post.builder()
//                .title(postRequest.title())
//                .content(postRequest.content())
//                .build();

        Post savedPost = postRepository.save(new Post(postCreateRequest));

//        return PostResponse.builder()
//                .id(savedPost.getId())
//                .title(savedPost.getTitle())
//                .content(savedPost.getContent())
//                .build();
        //return new PostResponse(savedPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PostResponse(savedPost));
    }

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        // ToRefactor : stream() 사용해서 코드 리팩토링 가능하지 않나?
//        List<Post> posts = postRepository.findAll();
//
//        List<PostResponse> postResponses =  new ArrayList<>();
//
//        for(Post post : posts){
//            postResponses.add(new PostResponse(post));
//        }

        List<PostResponse> postResponses = postRepository.findAll().stream()
                .map(PostResponse::new)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(postResponses);
    }

    // 특정 게시글 조회
    @Transactional(readOnly = true)
    public ResponseEntity<PostResponse> getPost(Long id){
        // ToRefactor : Optional 메서드 사용해보기, get() 사용 지양하기!
        return ResponseEntity.status(HttpStatus.OK).body(new PostResponse(postRepository.findOne(id)));
    }
    
    // 게시글 수정
    public ResponseEntity<PostResponse> updatePost(Long id, PostUpdateRequest postUpdateRequest){
        // 해당 id의 게시글이 존재하는지 확인 먼저할 것
        Post post = postRepository.findOne(id);
        
        // 게시글 수정
        post.updatePost(postUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new PostResponse(postRepository.save(post)));
    }

    // 게시글 삭제
    public ResponseEntity<Void> deletePost(Long id){
        // 해당 id의 게시글이 존재하는지 확인
        // 게시글 삭제
        postRepository.delete(postRepository.findOne(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
