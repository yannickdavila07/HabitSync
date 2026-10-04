package br.com.api.habitFlow.controller;

import br.com.api.habitFlow.dto.DadosAtualizacaoHabito;
import br.com.api.habitFlow.dto.DadosCadastroHabito;
import br.com.api.habitFlow.dto.DadosDetalhamentoHabito;
import br.com.api.habitFlow.dto.DadosListagemHabito;
import br.com.api.habitFlow.model.Frequency;
import br.com.api.habitFlow.service.HabitoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/habitos")
public class HabitoController {

    @Autowired
    private HabitoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoHabito> cadastrarHabito(@RequestBody DadosCadastroHabito dados, UriComponentsBuilder uriBuilder){
        var habito = service.cadastrarHabito(dados);
        var uri = uriBuilder.buildAndExpand("/{id}").toUri();
        return ResponseEntity.created(uri).body(habito);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoHabito> listarHabitoPorId(@PathVariable Long id){
        var habito = service.listarHabitoPorId(id);
        return ResponseEntity.ok().body(habito);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemHabito>> listarHabitos(@RequestParam(required = false)Frequency frequency, @RequestParam(required = false) Boolean active){
        var habitos = service.listarHabitos(frequency, active);
        return ResponseEntity.ok().body(habitos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoHabito> atualizarHabito(@RequestBody DadosAtualizacaoHabito dados){
        var habito = service.atualizarHabito(dados);
        return ResponseEntity.ok().body(habito);
    }

    @PatchMapping("/{id}/{validador}")
    @Transactional
    public ResponseEntity alterarHabito(@PathVariable Long id, @PathVariable String validador){
        service.alterarHabito(id, validador);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarHabito(@PathVariable Long id){
        service.deletarHabito(id);
        return ResponseEntity.noContent().build();
    }


}
