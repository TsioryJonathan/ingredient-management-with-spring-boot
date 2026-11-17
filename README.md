# Ingredient Management API

Spring Boot API for a restaurant kitchen: dishes, their ingredients and the stock of each ingredient.

## Endpoints

| Route | Description |
|---|---|
| `GET /dishes`, `GET /dishes/{id}` | List and read dishes |
| `PUT /dishes/{id}/ingredients` | Set the ingredients of a dish |
| `GET /ingredients`, `GET /ingredients/{id}` | List and read ingredients |
| `GET /ingredients/{id}/stock` | Current stock value |
| `GET /ingredients/{id}/stockMovements`, `POST /ingredients/{id}/stockMovements` | Stock movements (in and out) |

## Stack

Java, Spring Boot, PostgreSQL, JDBC repositories, a global exception handler for not-found errors.

## Run

Set `spring.datasource.*` in `src/main/resources/application.properties`, then:

```bash
./mvnw spring-boot:run
```
