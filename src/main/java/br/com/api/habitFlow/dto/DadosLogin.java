package br.com.api.habitFlow.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}
