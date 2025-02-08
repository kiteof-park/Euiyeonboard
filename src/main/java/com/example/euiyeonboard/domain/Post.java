package com.example.euiyeonboard.domain;

import com.example.euiyeonboard.dto.PostCreateRequest;
import com.example.euiyeonboard.dto.PostResponse;
import com.example.euiyeonboard.dto.PostUpdateRequest;
import jakarta.persistence.*;;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@AllArgsConstructor     // access = AccessLevel.?
@NoArgsConstructor      // access = AccessLevel.?
@Builder                // @Builder는 모든 필드를 초기화할 수 있는 생성자를 기반으로 동작 -> @AllArgsCosntructor가 필요
@Entity
@Table(name = "post")
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    // 생성자 오버로딩
    public Post(PostCreateRequest postCreateRequest) {
        this.title = postCreateRequest.title();
        this.content = postCreateRequest.content();
    }

    // 게시글 업데이트 메서드
    public void updatePost(PostUpdateRequest postUpdateRequest){
        this.title = postUpdateRequest.title();
        this.content = postUpdateRequest.content();
    }
}
