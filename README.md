# back-end-challenge-AmeDigital

### API STAR WARS

## Índice

 <ol>
  <li><a href="#Sobre">Sobre o projeto</a></li>
  <li><a href="#Tecnologias">Tecnologias utilizadas</a></li>
  <li><a href="#Pre">Pré-requisitos</a></li>
  <li><a href="#Instalacao">Instalação</a>
  <li><a href="#Rotas">Rotas</a>
  <li><a href="#Doc">Documentação</a>

</ol> 

<dl>

### <a name="Sobre">1. Sobre o projeto</a>

&nbsp;&nbsp;&nbsp;&nbsp;API que contém os dados dos planetas, como: nome, clima e terreno. Ao cadastrar, é feito uma
consulta (REST) a API pública do Star Wars (https://swapi.dev/) para saber a quantidade de aparições em filmes desse
planeta.

### <a name="Tecnologias">2. Tecnologias utilizadas</a>

- Java 17 com a IDE Intellij
- Spring Boot
- Docker
- PostgreSQL
- Testes com Postman
- Documentação com Swagger

### <a name="Pre">3. Pré-requisitos</a>

- Possuir o git
- Possuir o maven
- Possuir o docker ou PostgreSQL

### <a name="Instalacao">4. Instalação</a>

- Clonar o projeto
- Em seguida é necessário acessar a pasta do projeto e executar os comandos abaixo (se estiver usando docker):
    - *mvn clean package*
    - *docker-compose up*
- Feito isso, o projeto estará rodando em **http://localhost:8080**

### <a name="Rotas">5. Rotas</a>

| Requisição | Caminho                   | Função                     |
|------------|---------------------------|----------------------------|
| GET        | /planetas                 | Lista todos os planetas    
| POST       | /planetas/add?planetId=?  | Cria um planeta            
| DELETE     | /planetas/id/{id}         | Remove um planeta          
| GET        | /planetas/id/{id}         | Busca um planeta por ID    
| GET        | /planetas/nome/{nome}     | Busca um planeta pelo Nome 

### <a name="Doc">6. Documentação</a>

- O projeto foi documentado usando o Swagger, com isso basta acessar **http://localhost:8080/swagger-ui.html#/** para
  ter acesso aos endpoints.
- Use **http://localhost:8080/v3/api-docs** para ver documentação em JSON.

