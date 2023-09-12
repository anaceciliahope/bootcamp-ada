package tech.ada.avanade.bootcampada.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPersonagem tipoPersonagem;

    @Column(unique = true)
    private String nome;
    private Integer pontosVida;
    private Integer forca;
    private Integer defesa;
    private Integer agilidade;
    private Integer quantidadeDados;
    private Integer facesDado;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Personagem that = (Personagem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPersonagem getTipoPersonagem() {
        return tipoPersonagem;
    }

    public void setTipoPersonagem(TipoPersonagem tipoPersonagem) {
        this.tipoPersonagem = tipoPersonagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPontosVida() {
        return pontosVida;
    }

    public void setPontosVida(Integer pontosVida) {
        this.pontosVida = pontosVida;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
    }

    public Integer getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(Integer agilidade) {
        this.agilidade = agilidade;
    }

    public Integer getQuantidadeDados() {
        return quantidadeDados;
    }

    public void setQuantidadeDados(Integer quantidadeDados) {
        this.quantidadeDados = quantidadeDados;
    }

    public Integer getFacesDado() {
        return facesDado;
    }

    public void setFacesDado(Integer facesDado) {
        this.facesDado = facesDado;
    }
}
