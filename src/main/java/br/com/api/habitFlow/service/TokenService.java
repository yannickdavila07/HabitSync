package br.com.api.habitFlow.service;

import br.com.api.habitFlow.model.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    public String gerarToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("HabitFlow")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expiracao(30))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new SecurityException("Erro ao gerar TokenJWT");
        }
    }

    public String gerarRefreshToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("HabitFlow")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expiracao(120))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new SecurityException("Erro ao gerar TokenJWT");
        }
    }


    public String validarToken(String token){
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("HabitFlow")
                    .build();

            decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception){
            throw new SecurityException("Token inválido!");
        }
    }



    private Instant expiracao(int numero){
        return LocalDateTime.now().plusMinutes(numero).toInstant(ZoneOffset.of("-03:00"));

    }

}
