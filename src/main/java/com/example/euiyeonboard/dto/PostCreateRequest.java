package com.example.euiyeonboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

// RequestDTO에는 검증과 관련된 어노테이션 추가할 수 있음
@Builder
public record PostCreateRequest(
        @NotBlank(message = "제목은 필수값입니다.")
        @Size(max = 50, message = "제목은 50자 이하로 작성해주세요")
        String title,

        @NotBlank(message = "내용은 필수값입니다.")
        String content
) {
}
