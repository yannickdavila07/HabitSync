package br.com.api.habitFlow.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosRefreshToken(
        @NotBlank
        String refreshToken
) {
}
