package com.example.euiyeonboard.dto;

import com.example.euiyeonboard.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Builder        // @Builder 클래스 레벨 -> 모든 필드를 포함하는 생성자를 기준으로 동작
// @Getter      // 테스트 코드 작성을 위해 추가 -> 헀다가 뺌, record는 getter를 자동 추가
public record PostResponse(
        Long id,
        String title,
        String content
) {
    // 생성자 오버로딩
    public PostResponse(Post post){
        this(post.getId(), post.getTitle(), post.getContent());
    }
}
