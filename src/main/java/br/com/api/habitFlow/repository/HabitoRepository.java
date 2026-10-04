package br.com.api.habitFlow.repository;


import br.com.api.habitFlow.model.habito.Frequency;
import br.com.api.habitFlow.model.habito.Habito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitoRepository extends JpaRepository<Habito ,Long> {
    @Query("SELECT h FROM Habito h WHERE " +
            "(:frequency IS NULL OR h.frequency = :frequency) AND" +
            "(:active IS NULL OR h.active = :active)" )
    List<Habito> encontrarPersonalizado(Frequency frequency, Boolean active);
}
