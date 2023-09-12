package tech.ada.avanade.bootcampada.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.avanade.bootcampada.model.Duelo;

public interface DueloRepository extends JpaRepository<Duelo, Long> {
}
