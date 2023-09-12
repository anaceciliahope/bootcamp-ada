package tech.ada.avanade.bootcampada.model;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Acao {

    private Integer numeroSorteadoDado;
    @ManyToOne
    private Personagem personagem;
    public abstract Integer calcularDano();

    public Integer getNumeroSorteadoDado() {
        return numeroSorteadoDado;
    }

    public void setNumeroSorteadoDado(Integer numeroSorteadoDado) {
        this.numeroSorteadoDado = numeroSorteadoDado;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }
}
