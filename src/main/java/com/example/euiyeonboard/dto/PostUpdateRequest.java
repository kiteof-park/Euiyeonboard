package com.example.euiyeonboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostUpdateRequest(

        @NotBlank(message = "제목은 필수 입력값입니다.")
        @Size(min = 2, max = 30, message = "제목은 2글자 이상 30글자 이하로 작성해야 합니다.")
        String title,

        @NotBlank(message = "내용은 필수 입력값입니다.")
        String content
) {
}
