package tech.ada.avanade.bootcampada.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.ada.avanade.bootcampada.model.Personagem;

import java.util.List;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

    @Query(value = "select p.id from Personagem p where p.tipoPersonagem = 'MONSTRO'")
    List<Long> findIdMonstros();
}
