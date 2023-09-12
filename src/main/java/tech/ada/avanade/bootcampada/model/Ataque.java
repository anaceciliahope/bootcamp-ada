package tech.ada.avanade.bootcampada.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ataque extends Acao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Integer calcularDano() {
        Integer numeroSorteado = getNumeroSorteadoDado();
        Integer forca = getPersonagem().getForca();
        Integer agilidade = getPersonagem().getAgilidade();
        return numeroSorteado + forca + agilidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
