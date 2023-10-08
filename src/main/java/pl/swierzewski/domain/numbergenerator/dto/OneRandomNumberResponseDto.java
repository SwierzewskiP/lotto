package pl.swierzewski.domain.numbergenerator.dto;

import lombok.Builder;

@Builder
public record OneRandomNumberResponseDto(
        int number
) {
}
