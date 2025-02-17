package com.example.euiyeonboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostUpdateRequest(

        @NotBlank(message = "제목은 필수 입력값입니다.")
        @Size(min = 1, max = 15, message = "제목은 1글자 이상 15글자 이하로 작성해주세요.")
        String title,

        @NotBlank(message = "내용은 필수 입력값입니다.")
        @Size(min = 1, max = 1000, message = "내용은 1글자 이상 1000글자 이하로 작성해주세요.")
        String content
) {
}
