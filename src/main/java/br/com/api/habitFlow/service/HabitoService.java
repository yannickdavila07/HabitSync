package br.com.api.habitFlow.service;

import br.com.api.habitFlow.dto.DadosCadastroHabito;
import br.com.api.habitFlow.dto.DadosDetalhamentoHabito;
import br.com.api.habitFlow.dto.DadosListagemHabito;
import br.com.api.habitFlow.model.Habito;
import br.com.api.habitFlow.repository.HabitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitoService {
    @Autowired
    private HabitoRepository habitoRepository;

    public DadosDetalhamentoHabito cadastrarHabito(DadosCadastroHabito dados){
        var habito = new Habito(dados);
        habitoRepository.save(habito);
        return new DadosDetalhamentoHabito(habito);
    }

    public List<DadosListagemHabito> listarHabitos() {
        List<Habito> listaHabitos = habitoRepository.findAll();
        return listaHabitos.stream().map(habito -> new DadosListagemHabito(habito)).toList();
    }
}
