package br.com.api.habitFlow.controller;

import br.com.api.habitFlow.dto.DadosAtualizacaoHabito;
import br.com.api.habitFlow.dto.DadosCadastroHabito;
import br.com.api.habitFlow.dto.DadosDetalhamentoHabito;
import br.com.api.habitFlow.dto.DadosListagemHabito;
import br.com.api.habitFlow.model.habito.Frequency;
import br.com.api.habitFlow.model.user.User;
import br.com.api.habitFlow.service.HabitoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<DadosDetalhamentoHabito> cadastrarHabito(@RequestBody DadosCadastroHabito dados, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal User user){
        var habito = service.cadastrarHabito(dados, user);
        var uri = uriBuilder.buildAndExpand("/{id}").toUri();
        return ResponseEntity.created(uri).body(habito);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoHabito> listarHabitoPorId(@PathVariable Long id, @AuthenticationPrincipal User user){
        var habito = service.listarHabitoPorId(id, user);
        return ResponseEntity.ok().body(habito);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemHabito>> listarHabitos(@RequestParam(required = false)Frequency frequency, @RequestParam(required = false) Boolean active, @AuthenticationPrincipal User user){
        var habitos = service.listarHabitos(frequency, active, user);
        return ResponseEntity.ok().body(habitos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoHabito> atualizarHabito(@RequestBody DadosAtualizacaoHabito dados, @AuthenticationPrincipal User user){
        var habito = service.atualizarHabito(dados, user);
        return ResponseEntity.ok().body(habito);
    }

    @PatchMapping("/{id}/{validador}")
    @Transactional
    public ResponseEntity alterarHabito(@PathVariable Long id, @PathVariable String validador, @AuthenticationPrincipal User user){
        service.alterarHabito(id, validador, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarHabito(@PathVariable Long id, @AuthenticationPrincipal User user){
        service.deletarHabito(id, user);
        return ResponseEntity.noContent().build();
    }


}
