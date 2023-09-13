package tech.ada.avanade.bootcampada.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DadoService {

    private final Random random;

    public DadoService(Random random) {
        this.random = random;
    }

    public int sortearNumero(int min, int max) {
        return this.random.ints(min, max).findFirst().orElseThrow();
    }
}
