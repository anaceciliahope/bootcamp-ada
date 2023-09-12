package tech.ada.avanade.bootcampada.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import tech.ada.avanade.bootcampada.dto.DueloRequestDTO;
import tech.ada.avanade.bootcampada.dto.DueloResponseDTO;
import tech.ada.avanade.bootcampada.model.Duelo;
import tech.ada.avanade.bootcampada.model.Personagem;
import tech.ada.avanade.bootcampada.service.DueloService;

@RestController
@RequestMapping(value = "/duelos")
public class DueloController {

    public final DueloService service;
    private final ModelMapper mapper;

    public DueloController(DueloService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public DueloResponseDTO iniciarDuelo(@RequestBody @Valid DueloRequestDTO dueloRequestDTO) {
        Duelo duelo = new Duelo();
        duelo.setNomeDuelante(dueloRequestDTO.getNomeDuelante());
        Personagem duelante = new Personagem();
        duelante.setId(dueloRequestDTO.getIdPersonagemDuelante());
        duelo.setDuelante(duelante);
        if (dueloRequestDTO.getIdPersonagemOponente() != null) {
            Personagem oponente = new Personagem();
            oponente.setId(dueloRequestDTO.getIdPersonagemOponente());
            duelo.setOponente(oponente);
        }
        duelo = service.iniciarDuelo(duelo);
        return mapper.map(duelo, DueloResponseDTO.class);
    }

    @PatchMapping("/{id}/ataque")
    public DueloResponseDTO atacar(@PathVariable Long id) {
        System.out.println(id);
        Duelo duelo = service.atacar(id);
        return mapper.map(duelo, DueloResponseDTO.class);
    }
    @PatchMapping("/{id}/defesa")
    public DueloResponseDTO defender(@PathVariable Long id) {
        System.out.println(id);
        Duelo duelo = service.defender(id);
        return mapper.map(duelo, DueloResponseDTO.class);
    }
    @PatchMapping("/{id}/calculodano")
    public DueloResponseDTO calcularDano(@PathVariable Long id) {
        Duelo duelo = service.calcularDano(id);
        System.out.println(id);
        return mapper.map(duelo, DueloResponseDTO.class);

    }


}
