package tech.ada.avanade.bootcampada.service;

import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DadoServiceTest {

    private DadoService dadoService;

    @BeforeEach
    void setUp() {
        dadoService = new DadoService(new Random());
    }

    @Test
    @DisplayName("Deve sortear um numero entre 1 e 5")
    void deveSortearNumeroEntre1e5() {
        int numeroSorteado = dadoService.sortearNumero(1, 5);
        boolean sorteioCorreto = numeroSorteado >= 1 && numeroSorteado <=5;
        assertTrue(sorteioCorreto);
    }

    @Test
    @DisplayName("Deve levantar exceção IllegalArgumentException quando o limite for igual a origem")
    void deveLevantarExcecaoComLimiteIgualAOrigem() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> dadoService.sortearNumero(0, 0));
        assertNotNull(exception);
        assertEquals("bound must be greater than origin", exception.getMessage());
    }

}