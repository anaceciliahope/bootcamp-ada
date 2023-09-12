package tech.ada.avanade.bootcampada.dto;

import java.util.List;

public class DueloHistoicoResponseDTO extends DueloResponseDTO {

    private List<TurnoResponseDTO> turnos;

    public List<TurnoResponseDTO> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<TurnoResponseDTO> turnos) {
        this.turnos = turnos;
    }
}
