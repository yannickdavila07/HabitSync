package br.com.api.habitFlow.repository;


import br.com.api.habitFlow.model.Habito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitoRepository extends JpaRepository<Habito ,Long> {
}
