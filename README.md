# PostGIS Spring Boot API

Projeto desenvolvido para a disciplina de Banco de Dados II, utilizando **Spring Boot**, **PostgreSQL** e **PostGIS** para manipulação e consulta de dados geográficos (Municípios, Ferrovias e Portos).

## Tecnologias Utilizadas
* **Java 17+**
* **Spring Boot** (Spring Data JPA)
* **PostgreSQL** com extensão **PostGIS**
* **Springdoc OpenAPI (Swagger)** para documentação das rotas

## Endpoints da API

Abaixo estão listadas as principais rotas disponíveis na aplicação:

### Municípios
* `GET /municipiosVizinhos/{nome}`
* `GET /distanciaEntreMunicipios/{municipioA}/{municipioB}`
* `GET /municipiosPorUf/{sigla}`
* `GET /municipiosPorAreaMaiorQue/{area}`
* `GET /municipiosEmRaio/{nome}/{raio}`
* `GET /municipiosPorNomeContendo/{nome}`

### Ferrovias
* `GET /ferroviasPorOperadora/{operadora}`
* `GET /ferroviasPorNomeContendo/{nome}`
* `GET /ferroviasOrdenadas`

### Portos
* `GET /portosPorUf/{sigla}`
* `GET /portosPorNomeContendo/{nome}`
* `GET /portosPorNomeEstado/{nomeEstado}`
### Exemplos:
* `http://localhost:8080/municipiosVizinhos/Campina Grande`
* `http://localhost:8080/distanciaEntreMunicipios/Campina Grande/João Pessoa`
* `http://localhost:8080/municipiosPorUf/PB`
* `http://localhost:8080/municipiosPorAreaMaiorQue/50000`
* `http://localhost:8080/municipiosEmRaio/Campina Grande/500`
* `http://localhost:8080/municipiosPorNomeContendo/João`
* `http://localhost:8080/ferroviasPorOperadora/MMX Logística`
* `http://localhost:8080/ferroviasPorNomeContendo/Serra`
* `http://localhost:8080/ferroviasOrdenadas`
* `http://localhost:8080/portosPorUf/RS`
* `http://localhost:8080/portosPorNomeContendo/Rio`
* `http://localhost:8080/portosPorNomeEstado/Rio Grande do Sul`

## Como Executar

1. Certifique-se de ter o **PostgreSQL com PostGIS** instalado e configurado na sua máquina.
2. Importe os dados espaciais (IBGE) para o seu banco de dados.
3. Configure as credenciais de acesso ao banco no arquivo `src/main/resources/application.properties`.
4. Execute o projeto a partir da sua IDE ou via terminal com:
   ```bash
   ./mvnw spring-boot:run
