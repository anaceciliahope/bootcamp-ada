# bootcamp-ada
Batalha épica estilo Advanced Dangeous &amp; Dragons (AD&amp;D)

### Documentacao da API
[http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


### Materiais de referência
[Configuração do Swagger](https://medium.com/@raphaelbluteau/spring-boot-swagger-documentando-sua-api-automaticamente-27903293aeb6)

[Configuração do H2 para ambiente de desenvolvimento](https://medium.com/@berkaybasoz/spring-boot-h2-database-setup-6996a1c18cc)


# Principais regras de negócio

### 1 - Escolha o seu nome e personagem favorito (herói ou monstro)

Quando for criar um duelo o id do personagem duelante é obrigatorio
```java
public class DueloRequestDTO {

    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    private String nomeDuelante;
    @NotNull
    private Long idPersonagemDuelante;
    private Long idPersonagemOponente;
    //...
}
```
```java
@RestController
@RequestMapping(value = "/duelos")
public class DueloController {
    @PostMapping
    public DueloResponseDTO iniciarDuelo(@RequestBody @Valid DueloRequestDTO dueloRequestDTO) {
    //...
    }
}
```
### 2 - Se um personagem ficar com PV igual ou abaixo de zero então o oponente será o vencedor
Quando o ponto de vida do personagem que efetuou a defesa atingir 0 ou menos pontos o duelo é finalizado, o atributo ```situacaoDuelo``` é definido como ```SituacaoDuelo.FINALIZADO```
```java
@Service
public class DueloService {
    private void definirFinalDuelo(Duelo duelo, Integer pontosAtuais) {
        if (pontosAtuais <= 0) {
            duelo.setJogadorAtual(null);
            duelo.setSituacaoDuelo(SituacaoDuelo.FINALIZADO);
            //...
        }
    }
}
```
### 3 - O dano causado por um ataque depende da força do atacante e da defesa do defensor, enquanto o dano recebido depende da força do atacante e da resistência do defensor e da eficácia de sua defesa
Seleciona o personagem que recebeu o dano e recebe o ponto de vida dele
```java
@Service
public class DueloService {
   //...
   public Duelo calcularDano(Long id) {
       if (turno.getDefesa().getPersonagem().equals(duelo.getDuelante())) {
           Integer pontosAtuais = duelo.getPontosVidaDuelante() - dano;
           duelo.setPontosVidaDuelante(pontosAtuais);
           definirFinalDuelo(duelo, pontosAtuais);
       } else {
           Integer pontosAtuais = duelo.getPontosVidaOponente() - dano;
           duelo.setPontosVidaOponente(pontosAtuais);
           definirFinalDuelo(duelo, pontosAtuais);
       }
   }
   
   }
    //...
```
### 4 - Se no fim do turno nenhum personagem ficou com zero ou menos PV então a luta continua e o próximo turno se inicia imediatamente
Ao final do turno se o personagem atingir zero ou menos ponto de vida a luta vai continuar no próximo turno
```java
@Service
public class DueloService {
    private void validarDueloAtivo(Duelo duelo) {
        if (duelo.getPontosVidaDuelante() <= 0 || duelo.getPontosVidaOponente() <= 0) {
            throw new AvanadeException("Este duelo não está mais ativo, um dos personagens, atigiu zero ou menos pontos");
        }
    }
}


```
