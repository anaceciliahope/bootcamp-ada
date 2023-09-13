package tech.ada.avanade.bootcampada.dto;

import tech.ada.avanade.bootcampada.model.SituacaoDuelo;

public class DueloResponseDTO {
    private Long id;
    private String nomeDuelante;
    private PersonagemResponseDTO duelante;
    private PersonagemResponseDTO oponente;
    private PersonagemHistoricoDTO jogadorAtual;
    private PersonagemHistoricoDTO iniciante;
    private Integer pontosVidaDuelante;
    private Integer pontosVidaOponente;
    private SituacaoDuelo situacaoDuelo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDuelante() {
        return nomeDuelante;
    }

    public void setNomeDuelante(String nomeDuelante) {
        this.nomeDuelante = nomeDuelante;
    }

    public PersonagemResponseDTO getDuelante() {
        return duelante;
    }

    public void setDuelante(PersonagemResponseDTO duelante) {
        this.duelante = duelante;
    }

    public PersonagemResponseDTO getOponente() {
        return oponente;
    }

    public void setOponente(PersonagemResponseDTO oponente) {
        this.oponente = oponente;
    }

    public Integer getPontosVidaDuelante() {
        return pontosVidaDuelante;
    }

    public void setPontosVidaDuelante(Integer pontosVidaDuelante) {
        this.pontosVidaDuelante = pontosVidaDuelante;
    }

    public Integer getPontosVidaOponente() {
        return pontosVidaOponente;
    }

    public void setPontosVidaOponente(Integer pontosVidaOponente) {
        this.pontosVidaOponente = pontosVidaOponente;
    }

    public PersonagemHistoricoDTO getJogadorAtual() {
        return jogadorAtual;
    }

    public void setJogadorAtual(PersonagemHistoricoDTO jogadorAtual) {
        this.jogadorAtual = jogadorAtual;
    }

    public PersonagemHistoricoDTO getIniciante() {
        return iniciante;
    }

    public void setIniciante(PersonagemHistoricoDTO iniciante) {
        this.iniciante = iniciante;
    }

    public SituacaoDuelo getSituacaoDuelo() {
        return situacaoDuelo;
    }

    public void setSituacaoDuelo(SituacaoDuelo situacaoDuelo) {
        this.situacaoDuelo = situacaoDuelo;
    }
}
