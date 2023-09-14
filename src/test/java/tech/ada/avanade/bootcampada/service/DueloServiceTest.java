package tech.ada.avanade.bootcampada.service;

import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.*;

import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.avanade.bootcampada.exception.AvanadeException;
import tech.ada.avanade.bootcampada.model.*;
import tech.ada.avanade.bootcampada.repository.DueloRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DueloServiceTest {

    @InjectMocks
    private DueloService service;
    @Mock
    private DueloRepository repository;
    @Mock
    private PersonagemService personagemService;
    @Mock
    private DadoService dadoService;


    @Test
    void iniciarDueloComOponenteInformadoSucesso() {
        Duelo duelo = getDuelo();
        Personagem duelante = getPersonagemDuelante();
        Personagem oponente = getPersonagemOponente();
        when(personagemService.recuperarPersonagem(anyLong())).thenReturn(duelante, oponente);
        when(dadoService.sortearNumero(anyInt(), anyInt())).thenReturn(1, 2);
        when(repository.save(any())).thenReturn(duelo);
        Duelo response = service.iniciarDuelo(duelo);

        assertEquals(oponente, response.getJogadorAtual());
        assertEquals(oponente, response.getIniciante());
        verify(personagemService, times(2)).recuperarPersonagem(anyLong());
    }

    @Test
    void iniciarDueloSemOponenteInformadoSucesso() {
        Duelo duelo = getDuelo();
        duelo.setOponente(null);
        Personagem duelante = getPersonagemDuelante();
        Personagem oponente = getPersonagemOponente();
        when(personagemService.recuperarPersonagem(anyLong())).thenReturn(duelante, oponente);
        when(dadoService.sortearNumero(anyInt(), anyInt())).thenReturn(0, 1, 2);
        when(personagemService.reuperarIdMontros()).thenReturn(Arrays.asList(1L, 2L));
        when(repository.save(any())).thenReturn(duelo);
        service.iniciarDuelo(duelo);
        verify(personagemService, times(2)).recuperarPersonagem(anyLong());
        verify(personagemService, times(1)).reuperarIdMontros();
        verify(repository, times(1)).save(any());
        verify(dadoService, times(3)).sortearNumero(anyInt(), anyInt());
    }

    @Test
    void iniciarDueloSemOponenteInformadoErroNenhumMonstroCadastrado() {
        Duelo duelo = getDuelo();
        duelo.setOponente(null);
        Personagem duelante = getPersonagemDuelante();
        Personagem oponente = getPersonagemOponente();
        when(personagemService.recuperarPersonagem(anyLong())).thenReturn(duelante, oponente);
        when(personagemService.reuperarIdMontros()).thenReturn(new ArrayList<>());
        NoResultException exception = assertThrows(NoResultException.class, () -> service.iniciarDuelo(duelo));
        verify(personagemService, times(1)).recuperarPersonagem(anyLong());
        verify(personagemService, times(1)).reuperarIdMontros();
        assertEquals("Não existe monstro cadastrado", exception.getMessage());
    }

    @Test
    void iniciarDueloComOponenteInformadoError() {
        Duelo duelo = getDuelo();
        Personagem duelante = getPersonagemDuelante();
        Personagem oponente = getPersonagemOponente();
        oponente.setTipoPersonagem(TipoPersonagem.HEROI);
        when(personagemService.recuperarPersonagem(anyLong())).thenReturn(duelante, oponente);
        AvanadeException exception = assertThrows(AvanadeException.class, () -> service.iniciarDuelo(duelo));
        assertEquals("O personagem [2] não é um Monstro", exception.getMessage());
    }


    private Duelo getDuelo() {
        Duelo duelo = new Duelo();
        duelo.setId(1L);
        duelo.setNomeDuelante("Teste duelo sucesso");
        duelo.setPontosVidaOponente(10);
        duelo.setPontosVidaDuelante(10);
        duelo.setTurnos(new ArrayList<>());
        duelo.getTurnos().add(getTurno());
        Personagem duelante = new Personagem();
        duelante.setId(1L);
        duelo.setDuelante(duelante);
        Personagem oponente = new Personagem();
        oponente.setId(2L);
        duelo.setOponente(oponente);
        duelo.setJogadorAtual(duelante);
        return duelo;
    }

    private Turno getTurno() {
        Turno turno = new Turno();
        turno.setNumeroTurno(1);

        Ataque ataque = new Ataque();
        ataque.setPersonagem(getPersonagemDuelante());
        ataque.setNumeroSorteadoDado(10);
        turno.setAtaque(ataque);

        Defesa defesa = new Defesa();
        defesa.setPersonagem(getPersonagemDuelante());
        turno.setDefesa(defesa);
        return turno;
    }

    private Personagem getPersonagemDuelante() {
        Personagem personagem = new Personagem();
        personagem.setId(1L);
        personagem.setTipoPersonagem(TipoPersonagem.HEROI);
        personagem.setNome("Guerreiro");
        personagem.setPontosVida(20);
        personagem.setForca(6);
        personagem.setDefesa(5);
        personagem.setAgilidade(6);
        personagem.setQuantidadeDados(1);
        personagem.setFacesDado(12);
        return personagem;
    }

    private Personagem getPersonagemOponente() {
        Personagem personagem = new Personagem();
        personagem.setId(2L);
        personagem.setTipoPersonagem(TipoPersonagem.MONSTRO);
        personagem.setNome("Gigante");
        personagem.setPontosVida(34);
        personagem.setForca(10);
        personagem.setDefesa(4);
        personagem.setAgilidade(4);
        personagem.setQuantidadeDados(3);
        personagem.setFacesDado(6);
        return personagem;
    }

    @Test
    void atacar() {
        Duelo duelo = getDuelo();
        Optional<Duelo> dueloOpional = Optional.of(duelo);
        when(repository.findById(anyLong())).thenReturn(dueloOpional);
        when(repository.save(any())).thenReturn(duelo);
        Duelo response = service.atacar(anyLong());
        assertNotNull(response);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).save(any());
    }

    @Test
    void defender() {
        Duelo duelo = getDuelo();
        Optional<Duelo> dueloOpional = Optional.of(duelo);
        when(repository.findById(anyLong())).thenReturn(dueloOpional);
        when(repository.save(any())).thenReturn(duelo);
        Duelo response = service.defender(anyLong());
        assertNotNull(response);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).save(any());
    }

    @Test
    void defenderDefenderSemTurnoDefinido() {
        Duelo duelo = getDuelo();
        duelo.setTurnos(new ArrayList<>());
        Optional<Duelo> dueloOpional = Optional.of(duelo);
        when(repository.findById(anyLong())).thenReturn(dueloOpional);
        AvanadeException exception = assertThrows(AvanadeException.class, () -> service.defender(anyLong()));
        assertNotNull(exception);
        assertEquals("Nenhum Ataque não foi efetuado, aguarde o primeiro ataque, para efetuar a defesa", exception.getMessage());
    }

    @Test
    void defenderDefenderSemAtaqueEfetuado() {
        Duelo duelo = getDuelo();
        duelo.getTurnos().get(0).getDefesa().setNumeroSorteadoDado(22);
        Optional<Duelo> dueloOpional = Optional.of(duelo);
        when(repository.findById(anyLong())).thenReturn(dueloOpional);
        AvanadeException exception = assertThrows(AvanadeException.class, () -> service.defender(anyLong()));
        assertNotNull(exception);
        assertEquals("Você não foi atacado, aguarde o ataque para efetuar a defesa!", exception.getMessage());
    }

    @Test
    void calcularDano() {
    }
}