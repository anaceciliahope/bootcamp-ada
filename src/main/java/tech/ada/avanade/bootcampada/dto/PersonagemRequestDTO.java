package tech.ada.avanade.bootcampada.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import tech.ada.avanade.bootcampada.model.TipoPersonagem;

public class PersonagemRequestDTO {

    @NotNull(message = "Tipo de personagem é obrigatório")
    private TipoPersonagem tipoPersonagem;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @Min(value=0, message = "Pontos de vida deve ser um valor positivo")
    private Integer pontosVida;
    @Min(value=0, message = "Força deve ser um valor positivo")
    private Integer forca;
    @Min(value=0, message = "Defesa deve ser um valor positivo")
    private Integer defesa;
    @Min(value=0, message = "Agilidade deve ser um valor positivo")
    private Integer agilidade;
    @Min(value=0, message = "Quantidade de dados deve ser um valor positivo")
    private Integer quantidadeDados;
    @Min(value=0, message = "Faces de dados deve ser um valor positivo")
    private Integer facesDado;

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
