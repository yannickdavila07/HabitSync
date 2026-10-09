package br.com.api.habitFlow.service;

import br.com.api.habitFlow.dto.DadosLogin;
import br.com.api.habitFlow.dto.DadosRefreshToken;
import br.com.api.habitFlow.dto.DadosToken;
import br.com.api.habitFlow.model.user.User;
import br.com.api.habitFlow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public DadosToken gerarTokens(DadosLogin dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var token = tokenService.gerarToken((User) authentication.getPrincipal());
        var refreshToken = tokenService.gerarRefreshToken((User) authentication.getPrincipal());
        return new DadosToken(token, refreshToken);

    }


    public DadosToken gerarPeloRefreshToken(DadosRefreshToken dados) {
        var dadosRefresh = dados.refreshToken();
        var email = tokenService.validarToken(dadosRefresh);
        User usuario = userRepository.encontrarPeloEmail(email).orElseThrow( () -> new SecurityException("Usuário não encontrado!"));

        var token = tokenService.gerarToken(usuario);
        var refreshToken = tokenService.gerarRefreshToken(usuario);
        return new DadosToken(token, refreshToken);
    }
}
