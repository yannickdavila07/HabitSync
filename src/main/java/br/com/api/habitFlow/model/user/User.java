package br.com.api.habitFlow.model.user;

import br.com.api.habitFlow.dto.DadosCriarConta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nomeUsuario;

    private String nomeCompleto;

    @Column(unique = true)
    private String email;

    private String senha;

    private Boolean active;

    private Boolean verify;

    private String codigoVerificacao;

    private LocalDateTime codigoExpiracao;

    public User(DadosCriarConta dados, String senhaEncriptografada, String codigoVerificacao){
        this.nomeUsuario = dados.nomeUsuario();
        this.nomeCompleto = dados.nomeCompleto();
        this.email = dados.email();
        this.senha = senhaEncriptografada;
        this.active = true;
        this.verify = false;
        this.codigoVerificacao = codigoVerificacao;
        this.codigoExpiracao = LocalDateTime.now().plusMinutes(15);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void verificar() {
        this.verify = true;
        this.codigoExpiracao = null;
        this.codigoVerificacao = null;
    }

    public void mudarCodigo(String codigo) {
        this.codigoVerificacao = codigo;
        this.codigoExpiracao = LocalDateTime.now().plusMinutes(15);
    }
}
