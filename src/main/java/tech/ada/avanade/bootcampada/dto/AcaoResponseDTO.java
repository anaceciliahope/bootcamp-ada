package tech.ada.avanade.bootcampada.dto;

public class AcaoResponseDTO {

    private Integer numeroSorteadoDado;
    private PersonagemHistoricoDTO personagem;

    public Integer getNumeroSorteadoDado() {
        return numeroSorteadoDado;
    }

    public void setNumeroSorteadoDado(Integer numeroSorteadoDado) {
        this.numeroSorteadoDado = numeroSorteadoDado;
    }

    public PersonagemHistoricoDTO getPersonagem() {
        return personagem;
    }

    public void setPersonagem(PersonagemHistoricoDTO personagem) {
        this.personagem = personagem;
    }
}
