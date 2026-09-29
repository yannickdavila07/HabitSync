package br.com.api.habitFlow.controller;

import br.com.api.habitFlow.dto.DadosCadastroHabito;
import br.com.api.habitFlow.dto.DadosDetalhamentoHabito;
import br.com.api.habitFlow.service.HabitoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController()
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

}
