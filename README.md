# user

Uma aplicação que disponibiliza operações para um recurso (usuário)

## Arquitetura

### Estrutura do projeto

Foi realizado uma divisão na estrutura entre adaptadores (endpoints, documentação, mappers...) e core (domínio, regras
de negócio).

Através de ConstraintValidators aplico as regras requisitadas na camada de serviço. Decidi por aplicar as regras dos
validadores do DTO (idade, e-mail, CPF...) na camada da service através das anotações do Spring.

Para persistências, o projeto utiliza H2 (por padrão, se comporta como in-memory, ou seja, seu estado não é mantido após
shutdown. Pode se comportar como banco em arquivo dependendo de parametrizações).

Para updates parciais, optei por utilizar MapStruct, além de facilitar os mapeamentos entre beans (DTOs e entidades),
também gera código para update de beans conforme parametrizações (no meu caso, ignorar atributos não presentes).

## Dependências em uso

- **[Spring Boot](https://spring.io/projects/spring-boot)**
- **[springdoc-openapi](https://springdoc.org/)**
- **[MapStruct](https://mapstruct.org/)**
- **[JUnit](https://junit.org/junit5/)**
- **[H2](https://www.h2database.com/html/main.html)**

## Instalação

### Requerimentos

- JDK 17+
- Maven 3.8.x (**opcional**, wrapper incluso no projeto)
- Para utilizar wrapper, substituir mvn por mvnw (ou ./mvnw se UNIX)

#### Build do projeto

```bash
mvn package
```

#### Execução dos testes

```bash
mvn test
```

#### Execução da aplicação

```bash
mvn spring-boot:run
```

## Uso

[API Documentation](http://localhost:8080/swagger-ui/index.html)

A aplicação fornece um CRUD para as operações de criação, atualização, busca por identificador e busca por nome.

Após inicialização da aplicação, você pode consultar sua própria documentação através do link acima.
