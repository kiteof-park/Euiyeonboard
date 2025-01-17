package com.example.euiyeonboard.dto;

import com.example.euiyeonboard.domain.Post;
import lombok.Builder;
import lombok.Getter;

// @Builder
// @Getter
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

// @Builder : 클래스 레벨에서 사용 -> 모든 필드를 포함하는 생성자를 기준으로 동작
    // ❌@Builder 제거❌
    // @Builder는 필드가 많거나, 특정 필드만 선택적으로 초기화하는 경우 유용
    // record는 불변성을 강제하고, 모든 필드를 포함하는 생성자를 자동 생성
// @Getter : 테스트 코드 작성을 위해 추가 -> 했다가 뺌, record는 getter를 자동 추가