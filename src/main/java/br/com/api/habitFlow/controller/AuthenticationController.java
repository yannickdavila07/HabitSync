package br.com.api.habitFlow.controller;

import br.com.api.habitFlow.dto.DadosLogin;
import br.com.api.habitFlow.dto.DadosRefreshToken;
import br.com.api.habitFlow.dto.DadosToken;
import br.com.api.habitFlow.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
