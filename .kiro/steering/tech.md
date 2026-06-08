# Tech Stack

## Runtime

- **Java 25**
- **Spring Boot 4.0.3**
- **MongoDB** (local, `mongodb://localhost:27017/workshop_mongo`)

## Key Dependencies

| Library | Purpose |
|---|---|
| spring-boot-starter-data-mongodb | MongoDB via `MongoRepository` |
| spring-boot-starter-webmvc | REST controllers (`@RestController`) |
| spring-boot-starter-security | HTTP Basic auth, BCrypt |
| spring-boot-starter-validation | Bean Validation (`@NotBlank`, `@Email`) |
| Lombok 1.18.44 | `@Data`, `@RequiredArgsConstructor`, `@NoArgsConstructor`, etc. |
| MapStruct 1.6.3 | Compile-time mappers (`@Mapper(componentModel = "spring")`) |
| spring-boot-starter-data-mongodb-test | Embedded Mongo for tests |
| spring-boot-starter-webmvc-test | `MockMvc` test support |

## Build System

Maven with the Spring Boot Maven Plugin. Use the included wrapper — do not rely on a globally installed `mvn`.

## Common Commands

```cmd
# Compile
mvnw.cmd compile

# Run all tests
mvnw.cmd test

# Package (skip tests)
mvnw.cmd package -DskipTests

# Run the application
mvnw.cmd spring-boot:run
```

## Annotation Processor Order

The `maven-compiler-plugin` is configured with annotation processors in this exact order: `mapstruct-processor` → `lombok` → `lombok-mapstruct-binding`. This order is required for Lombok + MapStruct to work together. Do not change it.
