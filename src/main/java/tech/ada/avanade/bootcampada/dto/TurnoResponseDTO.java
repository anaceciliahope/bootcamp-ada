package tech.ada.avanade.bootcampada.dto;

public class TurnoResponseDTO {

    private Long id;
    private AcaoResponseDTO ataque;
    private AcaoResponseDTO defesa;
    private Integer dano;
    private Integer numeroTurno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcaoResponseDTO getAtaque() {
        return ataque;
    }

    public void setAtaque(AcaoResponseDTO ataque) {
        this.ataque = ataque;
    }

    public AcaoResponseDTO getDefesa() {
        return defesa;
    }

    public void setDefesa(AcaoResponseDTO defesa) {
        this.defesa = defesa;
    }

    public Integer getDano() {
        return dano;
    }

    public void setDano(Integer dano) {
        this.dano = dano;
    }

    public Integer getNumeroTurno() {
        return numeroTurno;
    }

    public void setNumeroTurno(Integer numeroTurno) {
        this.numeroTurno = numeroTurno;
    }
}
