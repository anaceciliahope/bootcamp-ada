# bootcamp-ada
Batalha épica estilo Advanced Dangeous &amp; Dragons (AD&amp;D)

### Documentacao da API
[http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


### Materiais de referência
[Configuração do Swagger](https://medium.com/@raphaelbluteau/spring-boot-swagger-documentando-sua-api-automaticamente-27903293aeb6)

[Configuração do H2 para ambiente de desenvolvimento](https://medium.com/@berkaybasoz/spring-boot-h2-database-setup-6996a1c18cc)


# Regras de negócio

### 1 - Escolha o seu nome e personagem favorito (herói ou monstro)

Quando for criar um duelo o id do personagem duelante é obrigatorio
```java
public class DueloRequestDTO {

    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    private String nomeDuelante;
    @NotNull
    private Long idPersonagemDuelante;
    private Long idPersonagemOponente;
    ...
}
```
```java
@PostMapping
public DueloResponseDTO iniciarDuelo(@RequestBody @Valid DueloRequestDTO dueloRequestDTO) {
    ...
}

```