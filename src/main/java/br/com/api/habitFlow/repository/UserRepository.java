package br.com.api.habitFlow.repository;

import br.com.api.habitFlow.model.user.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);

    @Query(value = "SELECT u FROM User u WHERE u.email = :email AND u.active = TRUE AND u.verify = TRUE")
    Optional<User> encontrarPeloEmail(String email);

    boolean existsByEmail(@NotBlank String email);

    boolean existsByNomeUsuario(@NotBlank String s);
}
