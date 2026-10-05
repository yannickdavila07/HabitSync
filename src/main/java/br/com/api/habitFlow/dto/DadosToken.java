package br.com.api.habitFlow.dto;

public record DadosToken(
        String token,
        String refreshToken
) {
}
