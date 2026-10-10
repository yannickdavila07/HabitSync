package br.com.api.habitFlow.service;

import br.com.api.habitFlow.dto.DadosCodigoVerificacao;
import br.com.api.habitFlow.dto.DadosCriarConta;
import br.com.api.habitFlow.infra.exception.ValidationException;
import br.com.api.habitFlow.model.user.User;
import br.com.api.habitFlow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.encontrarPeloEmail(username).orElseThrow(() -> new SecurityException("Usuário não encontrado!"));
    }



}
