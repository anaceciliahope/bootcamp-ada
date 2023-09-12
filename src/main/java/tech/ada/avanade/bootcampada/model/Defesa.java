package tech.ada.avanade.bootcampada.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Defesa extends Acao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Integer calcularDano() {
        Integer numeroSorteado = getNumeroSorteadoDado();
        Integer defesa = getPersonagem().getDefesa();
        Integer agilidade = getPersonagem().getAgilidade();
        return numeroSorteado + defesa + agilidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
