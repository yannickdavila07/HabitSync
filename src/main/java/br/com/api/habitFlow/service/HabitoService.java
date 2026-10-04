package br.com.api.habitFlow.service;

import br.com.api.habitFlow.dto.DadosAtualizacaoHabito;
import br.com.api.habitFlow.dto.DadosCadastroHabito;
import br.com.api.habitFlow.dto.DadosDetalhamentoHabito;
import br.com.api.habitFlow.dto.DadosListagemHabito;
import br.com.api.habitFlow.infra.exception.ValidationException;
import br.com.api.habitFlow.model.habito.Frequency;
import br.com.api.habitFlow.model.habito.Habito;
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

    public List<DadosListagemHabito> listarHabitos(Frequency frequency, Boolean active) {
        List<Habito> listaHabitos = habitoRepository.encontrarPersonalizado(frequency, active);
        return listaHabitos.stream().map(DadosListagemHabito::new).toList();
    }

    public DadosDetalhamentoHabito atualizarHabito(DadosAtualizacaoHabito dados) {
        var habito = habitoRepository.findById(dados.id())
                .orElseThrow( () -> new ValidationException("Hábito não encontrado!"));
        habito.atualizarInformacoes(dados);
        return new DadosDetalhamentoHabito(habito);
    }

    public void alterarHabito(Long id, String validador) {
        var habito = habitoRepository.findById(id).orElseThrow(() -> new ValidationException("Hábito não encontrado"));
        if (validador.equals("desativar")){
            habito.desativarHabito();
        }
        if (validador.equals("ativar")){
            habito.ativarHabito();
        }

    }

    public void deletarHabito(Long id) {
        var habito = habitoRepository.findById(id).orElseThrow(() -> new ValidationException("Hábito não encontrado"));
        habitoRepository.delete(habito);
    }

    public DadosDetalhamentoHabito listarHabitoPorId(Long id) {
        var habito = habitoRepository.findById(id).orElseThrow(() -> new ValidationException("Hábito não encontrado!"));
        return new DadosDetalhamentoHabito(habito);
    }
}
