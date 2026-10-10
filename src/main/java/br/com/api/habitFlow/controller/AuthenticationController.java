package br.com.api.habitFlow.controller;

import br.com.api.habitFlow.dto.*;
import br.com.api.habitFlow.service.AuthenticationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping
    public ResponseEntity<DadosToken> tokens(@RequestBody DadosLogin dados){
        var tokens = service.gerarTokens(dados);
        return ResponseEntity.ok().body(tokens);

    }

    @PostMapping
    @RequestMapping("/refresh")
    public ResponseEntity<DadosToken> refreshToken(@RequestBody DadosRefreshToken dados){
        var tokens = service.gerarPeloRefreshToken(dados);
        return ResponseEntity.ok().body(tokens);
    }

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity criarConta(@RequestBody DadosCriarConta dados){
        service.criarConta(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body("Código enviado com sucesso!");
    }

    @PostMapping
    @RequestMapping("/verificar")
    public ResponseEntity verificarConta(@RequestBody DadosCodigoVerificacao dados, @RequestParam String email){
        service.verificarConta(dados, email);
        return ResponseEntity.ok().body("Conta verificada com sucesso!!!");
    }

    @PostMapping
    @RequestMapping("/reenviarcodigo")
    public ResponseEntity reenviarCodigo(@RequestParam String email){
        service.reenviarCodigo(email);
        return ResponseEntity.ok().body("Código enviado com sucesso!");
    }
}
