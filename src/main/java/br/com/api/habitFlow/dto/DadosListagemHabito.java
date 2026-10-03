package br.com.api.habitFlow.dto;

import br.com.api.habitFlow.model.Frequency;
import br.com.api.habitFlow.model.Habito;
import br.com.api.habitFlow.model.Unit;

public record DadosListagemHabito(
        String name,
        String description,
        Frequency frequency,
        Integer target,
        Unit unit,
        Boolean active
) {
    public DadosListagemHabito(Habito habito) {
        this(habito.getName(), habito.getDescription(), habito.getFrequency(), habito.getTarget(), habito.getUnit(), habito.getActive());
    }
}
