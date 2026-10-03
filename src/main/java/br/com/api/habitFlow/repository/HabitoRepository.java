package br.com.api.habitFlow.repository;


import br.com.api.habitFlow.model.Habito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitoRepository extends JpaRepository<Habito ,Long> {

}
