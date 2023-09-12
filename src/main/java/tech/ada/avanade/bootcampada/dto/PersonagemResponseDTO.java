package tech.ada.avanade.bootcampada.dto;

import tech.ada.avanade.bootcampada.model.TipoPersonagem;

public class PersonagemResponseDTO extends PersonagemRequestDTO {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
