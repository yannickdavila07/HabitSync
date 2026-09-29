package br.com.api.habitFlow.dto;

import br.com.api.habitFlow.model.Frequency;
import br.com.api.habitFlow.model.Unit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroHabito(
        @NotBlank
        String name,
        String description,
        @NotNull
        Frequency frequency,
        @NotNull
        Integer target,
        @NotNull
        Unit unit


) {
}
