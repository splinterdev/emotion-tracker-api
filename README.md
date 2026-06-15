# Emotion Tracker - API de gerenciamento de emoções 🧠
![Java 21.0.6](https://img.shields.io/badge/Java-21.0.6-f08c00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot 4.0.2](https://img.shields.io/badge/Spring_Boot-4.0.2-6DB33F?style=flat&logo=springboot&logoColor=white)
![MySQL 8.0](https://img.shields.io/badge/MySQL-8.0-0A2740?style=flat&logo=mysql&logoColor=white)
![OpenAPI 2.8.14](https://img.shields.io/badge/OpenAPI-3.0.1-3C873A?style=flat&logo=swagger&logoColor=white)

API REST de gerenciamento de emoções que permite realizar registros diários de humor, sentimentos e situações específicas do dia a dia, além de permitir a visualização de histórico dos registros e a visualização de tendência de humor por meio de gráfico, a fim de trazer clareza emocional para o usuário final, diminuindo problemas como o **viés da negatividade** e **padrões disfuncionais de comportamento**.

## Principais tecnologias utilizadas 

- Java 21
- Spring Boot 4.0.2 **para construção da API**
- Swagger/OpenAPI 3.0.1 **para documentação**
- imagem MySQL 8.0 + contêiner Docker **para persistência de dados**
- Lombok **para código boilerplate**
- Mapstruct **para mapeamento entre DTOs e entidades**

## Endpoints

| Método | Endpoint                                 | Descrição                                                                                                         | Status Sucesso       | Status Erro                                                            |
|--------|------------------------------------------|-------------------------------------------------------------------------------------------------------------------|----------------------|------------------------------------------------------------------------|
| POST   | `/records`                               | Salva um novo registro                                                                                            | 201 Created          | 400 Bad Request, 422 Unprocessable Content                             |
| GET    | `/records`                               | Lista todos os registros cadastrados                                                                              | 200 OK               | -                                                                      |
| GET    | `/records/{id}`                          | Busca um registro específico por ID                                                                               | 200 OK               | 404 Not Found                                                          |
| GET    | `/records/moodstats/{currentDate}`| Retorna pontos (X e Y) dos últimos 7 dias baseado na data da requisição, onde X é um dia e Y é a média de humor desse dia | 200 OK               | 400 Bad Request, 422 Unprocessable Content                             |
| PUT    | `/records/{id}`                          | Atualiza um registro específico por ID                                                                            | 200 OK               | 400 Bad Request, 404 Not Found, 422 Unprocessable Content              |
| DELETE | `/records/{id}`                          | Deleta um registro específico por ID                                                                              | 204 No Content       | 404 Not Found                                                          |

<br>

### Exemplo de corpo JSON para métodos POST e PUT em /records

```json
{
  "recordDate": "11-10-2026 15:01",
  "mood": "MAL",
  "emotions": ["MEDO", "FRUSTRACAO"],
  "situation": {
     "title": "Um titulo curto para a situação",
     "thought": "O que eu pensei nessa situação",
     "behavior": "Como eu agi nessa situação"
  }
}
```
ou
```json
{
  "recordDate": "11-10-2026 15:01",
  "mood": "BEM",
  "emotions": ["FELICIDADE", "PAZ"],
  "description": "Uma descrição curta sobre o momento presente."
}
```

## Estrutura

```
src/main/java/com.github.IsaacMartins.emotionTrackerApi/
├── configuration/
├── controller/
│   └── common/
│   └── dto/
│   |   └── recordDTOs/
│   |   └── moodStatsDTOs/
│   └── mapper/
|
├── exception/
├── model/
│   └── entities/
│   └── enums/
|
├── repository/
├── service/
├── validator/
└── Program.java
```

