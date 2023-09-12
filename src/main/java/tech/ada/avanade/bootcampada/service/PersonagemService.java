package tech.ada.avanade.bootcampada.service;

import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;
import tech.ada.avanade.bootcampada.model.Personagem;
import tech.ada.avanade.bootcampada.repository.PersonagemRepository;

import java.util.List;

@Service
public class PersonagemService {

    private final PersonagemRepository repository;

    public PersonagemService(PersonagemRepository repository) {
        this.repository = repository;
    }

    public Personagem recuperarPersonagem(Long id) {
        return this.repository.findById(id).orElseThrow(
                () -> new NoResultException("Personagem [" + id + "] não cadastrado"));
    }

    public Personagem cadastrarPersonagem(Personagem personagem) {
        this.repository.save(personagem);
        return personagem;
    }

    public Personagem atualizarPersonagem(Personagem personagem) {
        Personagem old = recuperarPersonagem(personagem.getId());
        old.setTipoPersonagem(personagem.getTipoPersonagem());
        old.setNome(personagem.getNome());
        old.setPontosVida(personagem.getPontosVida());
        old.setForca(personagem.getForca());
        old.setDefesa(personagem.getDefesa());
        old.setAgilidade(personagem.getAgilidade());
        old.setQuantidadeDados(personagem.getQuantidadeDados());
        old.setFacesDado(personagem.getFacesDado());
        this.repository.save(old);
        return old;
    }

    public void deletePersonagem(Long id) {
        Personagem personagem = this.recuperarPersonagem(id);
        this.repository.delete(personagem);
    }

    public List<Personagem> recuperarPersonagens() {
        return this.repository.findAll();
    }

    public List<Long> reuperarIdMontros() {
        return repository.findIdMonstros();
    }

}
