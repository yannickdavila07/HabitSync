package br.com.api.habitFlow.dto;

import br.com.api.habitFlow.model.Frequency;
import br.com.api.habitFlow.model.Unit;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoHabito(
        @NotNull
        Long id,
        String name,
        String description,
        Frequency frequency,
        Integer target,
        Unit unit
) {
}
