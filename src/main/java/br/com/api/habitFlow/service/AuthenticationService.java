package br.com.api.habitFlow.service;

import br.com.api.habitFlow.dto.*;
import br.com.api.habitFlow.infra.exception.ValidationException;
import br.com.api.habitFlow.model.user.User;
import br.com.api.habitFlow.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Transactional
    public void criarConta(DadosCriarConta dados) {
        // 1. Validar se o e-mail já está em uso
        if (userRepository.existsByEmail(dados.email())) {
            throw new ValidationException("Já existe uma conta cadastrada com este e-mail.");
        }
        // 2. Validar se o username/nome de usuário já está em uso
        if (userRepository.existsByNomeUsuario(dados.nomeUsuario())) {
            throw new ValidationException("Este nome de usuário já está em uso.");
        }
        var senhaEncriptografada = passwordEncoder.encode(dados.senha());
        var codigoVerificacao = codigoEmail();
        User user = new User(dados, senhaEncriptografada, codigoVerificacao);

        emailService.enviarCodigoVerificacao(dados.email(), codigoVerificacao);
        userRepository.save(user);
    }


    private String codigoEmail(){
        Random random = new Random();
        int numero = random.nextInt(1000000);
        return String.format("%6d", numero);
    }

    @Transactional
    public void verificarConta(DadosCodigoVerificacao dados, String email) {
        var user = userRepository.findByEmail(email).orElseThrow();
        var codigo = dados.codigoVerificacao();

        if (!codigo.equals(user.getCodigoVerificacao())){
            throw new ValidationException("Codigo inválido!");
        }
        if (LocalDateTime.now().isAfter(user.getCodigoExpiracao())){
            throw new ValidationException("Codigo expirado!");
        }
        user.verificar();
        userRepository.save(user);
    }

    @Transactional
    public void reenviarCodigo(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new ValidationException("Email inválido! Crie a conta primeiro"));
        var codigo = codigoEmail();
        emailService.enviarCodigoVerificacao(user.getEmail(), codigo);
        user.mudarCodigo(codigo);

    }
}
