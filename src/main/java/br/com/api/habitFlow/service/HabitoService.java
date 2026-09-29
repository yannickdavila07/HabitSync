package br.com.api.habitFlow.service;

import br.com.api.habitFlow.dto.DadosCadastroHabito;
import br.com.api.habitFlow.dto.DadosDetalhamentoHabito;
import br.com.api.habitFlow.model.Habito;
import br.com.api.habitFlow.repository.HabitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitoService {
    @Autowired
    private HabitoRepository habitoRepository;

    public DadosDetalhamentoHabito cadastrarHabito(DadosCadastroHabito dados){
        var habito = new Habito(dados);
        habitoRepository.save(habito);
        return new DadosDetalhamentoHabito(habito);
    }
}
