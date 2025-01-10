package com.example.euiyeonboard.dto;

import lombok.Builder;

@Builder
public record PostResponse(
        Long id,
        String title,
        String content
) {
}
