package tech.ada.avanade.bootcampada.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DueloRequestDTO {

    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    private String nomeDuelante;
    @NotNull
    private Long idPersonagemDuelante;
    private Long idPersonagemOponente;

    public String getNomeDuelante() {
        return nomeDuelante;
    }

    public void setNomeDuelante(String nomeDuelante) {
        this.nomeDuelante = nomeDuelante;
    }

    public Long getIdPersonagemDuelante() {
        return idPersonagemDuelante;
    }

    public void setIdPersonagemDuelante(Long idPersonagemDuelante) {
        this.idPersonagemDuelante = idPersonagemDuelante;
    }

    public Long getIdPersonagemOponente() {
        return idPersonagemOponente;
    }

    public void setIdPersonagemOponente(Long idPersonagemOponente) {
        this.idPersonagemOponente = idPersonagemOponente;
    }
}
