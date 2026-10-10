package br.com.api.habitFlow.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCriarConta(
        @NotBlank
        String nomeUsuario,
        @NotBlank
        String nomeCompleto,
        @NotBlank
        String email,
        @NotBlank
        String senha
) {
}
