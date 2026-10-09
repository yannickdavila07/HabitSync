package br.com.api.habitFlow.repository;

import br.com.api.habitFlow.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);

    @Query(value = "SELECT u FROM User u WHERE u.email = :email AND u.active = TRUE")
    Optional<User> encontrarPeloEmail(String email);
}
