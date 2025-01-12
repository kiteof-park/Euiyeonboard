package com.example.euiyeonboard.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
// @Getter     // 테스트 코드 작성을 위해 추가 -> 헀다가 뺌, record는 getter를 자동 추가
public record PostResponse(
        Long id,
        String title,
        String content
) {
}
