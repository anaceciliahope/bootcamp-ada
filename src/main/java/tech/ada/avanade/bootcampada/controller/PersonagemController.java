package tech.ada.avanade.bootcampada.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.ada.avanade.bootcampada.dto.PersonagemRequestDTO;
import tech.ada.avanade.bootcampada.dto.PersonagemResponseDTO;
import tech.ada.avanade.bootcampada.model.Personagem;
import tech.ada.avanade.bootcampada.service.PersonagemService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/personagens")
public class PersonagemController {

    private final PersonagemService service;
    private final ModelMapper mapper;

    public PersonagemController(PersonagemService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public PersonagemResponseDTO getPersonagem(@PathVariable Long id) {
        Personagem personagem = this.service.recuperarPersonagem(id);
        return mapper.map(personagem, PersonagemResponseDTO.class);
    }

    @GetMapping
    public List<PersonagemResponseDTO> getPersonagens() {
        List<Personagem> personagens = service.recuperarPersonagens();
        List<PersonagemResponseDTO> responseList = new ArrayList<>();
        for (Personagem resp : personagens) {
            responseList.add(mapper.map(resp, PersonagemResponseDTO.class));
        }
        return responseList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonagemResponseDTO postPersonagem(@RequestBody @Valid PersonagemRequestDTO personagemRequestDTO) {
        Personagem personagem = this.service.cadastrarPersonagem(mapper.map(personagemRequestDTO, Personagem.class));
        return mapper.map(personagem, PersonagemResponseDTO.class);
    }

    @PutMapping("/{id}")
    public PersonagemResponseDTO putPersonagem(@RequestBody @Valid PersonagemRequestDTO personagemRequestDTO, @PathVariable Long id) {
        Personagem personagem = mapper.map(personagemRequestDTO, Personagem.class);
        personagem.setId(id);
        personagem = this.service.atualizarPersonagem(personagem);
        return mapper.map(personagem, PersonagemResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deletePersonagem(@PathVariable Long id) {
        this.service.deletePersonagem(id);
    }

}
