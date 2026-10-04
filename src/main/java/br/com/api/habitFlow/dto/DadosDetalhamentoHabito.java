package br.com.api.habitFlow.dto;

import br.com.api.habitFlow.model.habito.Frequency;
import br.com.api.habitFlow.model.habito.Habito;
import br.com.api.habitFlow.model.habito.Unit;

import java.time.LocalDateTime;

public record DadosDetalhamentoHabito(
        //Atributos que eu quero que apareça na requisicão
        Long id,
        String name,
        String description,
        Frequency frequency,
        Integer target,
        Unit unit,
        Boolean active,
        LocalDateTime createAt

) {
    //Um construtor para um Habito se transformar em um DTO para a exibição dos dados
    public DadosDetalhamentoHabito(Habito habito) {
        this(habito.getId(), habito.getName(), habito.getDescription(), habito.getFrequency(), habito.getTarget(), habito.getUnit(), habito.getActive(), habito.getCreateAt());
    }
}
