package tech.ada.avanade.bootcampada.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.avanade.bootcampada.dto.DueloHistoicoResponseDTO;
import tech.ada.avanade.bootcampada.model.Duelo;
import tech.ada.avanade.bootcampada.service.DueloService;

@RestController
@RequestMapping(value = "/historicos")
public class HistoricoController {

    private final DueloService service;
    private final ModelMapper mapper;

    public HistoricoController(DueloService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/duelos/{id}")
    public DueloHistoicoResponseDTO detalharDuelo(@PathVariable Long id) {
        Duelo duelo = service.recuperarDuelo(id);
        return mapper.map(duelo, DueloHistoicoResponseDTO.class);
    }


}
