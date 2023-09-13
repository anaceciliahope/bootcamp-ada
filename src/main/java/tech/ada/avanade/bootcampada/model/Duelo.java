package tech.ada.avanade.bootcampada.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Duelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeDuelante;
    @ManyToOne
    private Personagem duelante;
    @ManyToOne
    private Personagem oponente;
    @ManyToOne
    private Personagem jogadorAtual;
    @ManyToOne
    private Personagem iniciante;
    private Integer pontosVidaDuelante;
    private Integer pontosVidaOponente;
    @Enumerated(EnumType.STRING)
    private SituacaoDuelo situacaoDuelo;

    @OneToMany(mappedBy = "duelo", cascade = CascadeType.ALL)
    private List<Turno> turnos;

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

    public Personagem getDuelante() {
        return duelante;
    }

    public void setDuelante(Personagem duelante) {
        this.duelante = duelante;
    }

    public Personagem getOponente() {
        return oponente;
    }

    public void setOponente(Personagem oponente) {
        this.oponente = oponente;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
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

    public Personagem getJogadorAtual() {
        return jogadorAtual;
    }

    public void setJogadorAtual(Personagem jogadorAtual) {
        this.jogadorAtual = jogadorAtual;
    }

    public Personagem getIniciante() {
        return iniciante;
    }

    public void setIniciante(Personagem iniciante) {
        this.iniciante = iniciante;
    }

    public SituacaoDuelo getSituacaoDuelo() {
        return situacaoDuelo;
    }

    public void setSituacaoDuelo(SituacaoDuelo situacaoDuelo) {
        this.situacaoDuelo = situacaoDuelo;
    }
}
