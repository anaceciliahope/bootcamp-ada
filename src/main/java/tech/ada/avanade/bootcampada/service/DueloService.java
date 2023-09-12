package tech.ada.avanade.bootcampada.service;

import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.avanade.bootcampada.exception.AvanadeException;
import tech.ada.avanade.bootcampada.model.*;
import tech.ada.avanade.bootcampada.repository.DueloRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DueloService {

    @Autowired
    private DueloRepository repository;
    @Autowired
    private Random random;
    @Autowired
    private PersonagemService personagemService;

    public Duelo iniciarDuelo(Duelo duelo) {
        Personagem duelante = personagemService.recuperarPersonagem(duelo.getDuelante().getId());
        duelo.setDuelante(duelante);
        if (duelo.getOponente() != null) {
            Personagem oponente = personagemService.recuperarPersonagem(duelo.getOponente().getId());
            duelo.setOponente(oponente);
        } else {
            duelo.setOponente(recuperarOponenteAleatorio());
        }
        duelo.setPontosVidaDuelante(duelo.getDuelante().getPontosVida());
        duelo.setPontosVidaOponente(duelo.getOponente().getPontosVida());
        duelo.setJogadorAtual(sortearJogadorAtual(duelo));
        duelo = repository.save(duelo);
        return duelo;
    }

    private Personagem sortearJogadorAtual(Duelo duelo) {
        int min = 1;
        int max = 20;
        int numeroSorteadoDuelante = random.ints(min, max).findFirst().orElseThrow();
        int numeroSorteadoOponente = random.ints(min, max).findFirst().orElseThrow();
        while (numeroSorteadoDuelante == numeroSorteadoOponente) {
            numeroSorteadoDuelante = random.ints(min, max).findFirst().orElseThrow();
            numeroSorteadoOponente = random.ints(min, max).findFirst().orElseThrow();
        }
        return numeroSorteadoOponente > numeroSorteadoDuelante ? duelo.getOponente() : duelo.getDuelante();
    }

    private Personagem recuperarOponenteAleatorio() {
        List<Long> idMonstros = personagemService.reuperarIdMontros();
        if (!idMonstros.isEmpty()) {
            int sorteado = random.ints( 0, idMonstros.size()).findFirst().orElseThrow();
            return personagemService.recuperarPersonagem(idMonstros.get(sorteado));
        } else {
            throw new NoResultException("Não existe monstro cadastrado");
        }
    }

    public Duelo atacar(Long id) {
        Duelo duelo = recuperarDuelo(id);
        validarAtaque(duelo.getTurnos());
        Turno turno = new Turno();
        turno.setNumeroTurno(recuperarNumeroTurno(duelo.getTurnos()));
        turno.setDuelo(duelo);

        Ataque ataque = new Ataque();
        ataque.setPersonagem(duelo.getJogadorAtual());
        ataque.setNumeroSorteadoDado(sortearNumero(1, 12, 1));
        turno.setAtaque(ataque);

        Defesa defesa = new Defesa();
        defesa.setPersonagem(duelo.getJogadorAtual().equals(duelo.getDuelante()) ? duelo.getOponente() : duelo.getDuelante());
        turno.setDefesa(defesa);
        duelo.getTurnos().add(turno);

        alternarJogador(duelo);
        repository.save(duelo);
        return duelo;
    }

    public Duelo defender(Long id) {
        Duelo duelo = recuperarDuelo(id);
        Turno turno = recuperarUltimoTurno(duelo.getTurnos());
        validarDefesa(turno);
        Defesa defesa = turno.getDefesa();
        defesa.setNumeroSorteadoDado(sortearNumero(1, 12, 1));
        repository.save(duelo);
        return duelo;
    }

    public Duelo calcularDano(Long id) {
        Duelo duelo = recuperarDuelo(id);
        Turno turno = recuperarUltimoTurno(duelo.getTurnos());
        validarTurno(turno);
        Integer ataque = turno.getAtaque().calcularDano();
        Integer defesa = turno.getDefesa().calcularDano();
        Integer dano = 0;
        if (ataque > defesa) {
            int min = 1;
            int max = turno.getAtaque().getPersonagem().getFacesDado();
            int quantidadeDados = turno.getAtaque().getPersonagem().getQuantidadeDados();
            dano = sortearNumero(min, max, quantidadeDados);
            //se o personagem que defendeu for duelante, tiro pontos do duelante
            if (turno.getDefesa().getPersonagem().equals(duelo.getDuelante())) {
                Integer pontosAtuais = duelo.getPontosVidaDuelante() - dano;
                duelo.setPontosVidaDuelante(pontosAtuais);
            } else {
                //se o personagem que defendeu nao for duelante, tiro pontos do oponente
                Integer pontosAtuais = duelo.getPontosVidaOponente() - dano;
                duelo.setPontosVidaOponente(pontosAtuais);
            }
        }
        turno.setDano(dano);
        repository.save(duelo);
        return duelo;
    }

    private void validarTurno(Turno turno) {
        if (turno == null) {
            throw new AvanadeException("Não existe turno a ser validado");
        }
        if (turno.getDano() != null) {
            throw new AvanadeException("Já foi calculado o dano para este turno");
        }
        if (turno.getDefesa().getNumeroSorteadoDado() == null) {
            throw new AvanadeException("O Personagem ainda não efetuou a defesa do turno");
        }
    }

    private void validarDefesa(Turno turno) {
        if (turno == null) {
            throw new AvanadeException("Nenhum Ataque não foi efetuado, aguarde o primeiro ataque, para efetuar a defesa");
        } else if (turno.getDefesa().getNumeroSorteadoDado() != null) {
            throw new AvanadeException("Você não foi atacado, aguarde o ataque para efetuar a defesa!");
        }
    }


    private void validarDueloAtivo(Duelo duelo) {
        if (duelo.getPontosVidaDuelante() <= 0 || duelo.getPontosVidaOponente() <= 0) {
            throw new AvanadeException("Este duelo não está mais ativo, um dos personagens, atigiu zero ou menos pontos");
        }
    }

    private void alternarJogador(Duelo duelo) {
        if (duelo.getJogadorAtual().equals(duelo.getDuelante())) {
            duelo.setJogadorAtual(duelo.getOponente());
        } else {
            duelo.setJogadorAtual(duelo.getDuelante());
        }
    }

    private Integer sortearNumero(int min, int max, int quantidadeDados) {
        int numeroSorteado = 0;
        for (int i = 0; i < quantidadeDados; i++) {
            numeroSorteado += random.ints(min, max).findFirst().orElseThrow();
        }
        return numeroSorteado;
    }

    private Integer recuperarNumeroTurno(List<Turno> turnos) {
        Integer maiorTurno = 0;
        for (Turno turno: turnos) {
            if (maiorTurno < turno.getNumeroTurno()) {
                maiorTurno = turno.getNumeroTurno();
            }
        }
        return maiorTurno + 1;
    }

    private void validarAtaque(List<Turno> turnos) {
        Turno turno = recuperarUltimoTurno(turnos);
        //Se a defesa do ultimo turno ainda nao foi feita, nao pode atacar
        if (turno != null && turno.getDefesa() == null) {
            throw new AvanadeException("O Ataque deste Turno já foi efetuado");
        }
    }

    private Turno recuperarUltimoTurno(List<Turno> turnos) {
        Integer maiorTurno = 0;
        Turno turno = null;
        for (Turno t: turnos) {
            if (maiorTurno < t.getNumeroTurno()) {
                maiorTurno = t.getNumeroTurno();
                turno = t;
            }
        }
        return turno;
    }

    public Duelo recuperarDuelo(Long id) {
        Duelo duelo = this.repository.findById(id).orElseThrow(
                () -> new NoResultException("Duelo [" + id + "] não cadastrado"));
        validarDueloAtivo(duelo);
        return duelo;
    }

}
