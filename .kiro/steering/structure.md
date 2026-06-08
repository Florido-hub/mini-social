# Project Structure

Base package: `com.florido.workshopmongo`

```
src/main/java/com/florido/workshopmongo/
├── Application.java                  # Spring Boot entry point
│
├── command/                          # Write side (mutations)
│   ├── user/
│   │   ├── UserCommandDTO.java       # Input record (validated)
│   │   ├── UserCommandResource.java  # POST / PATCH / DELETE controller
│   │   └── UserCommandService.java   # Business logic for writes
│   └── post/
│       ├── PostCommandDTO.java
│       ├── CommentDTO.java
│       ├── PostCommandResource.java
│       └── PostCommandService.java
│
├── query/                            # Read side (queries)
│   ├── user/
│   │   ├── UserDTO.java              # Output record
│   │   ├── UserQueryResource.java    # GET controllers
│   │   └── UserQueryService.java     # @Transactional(readOnly = true)
│   └── post/
│       ├── PostDTO.java
│       ├── AuthorDTO.java            # Denormalized author snapshot
│       ├── PostQueryResource.java
│       └── PostQueryService.java
│
└── common/                           # Shared infrastructure
    ├── config/
    │   ├── SecurityConfiguration.java
    │   └── security/
    │       ├── CustomUserDetailsService.java
    │       └── SecurityService.java  # Helper to get the authenticated User
    ├── exceptions/
    │   ├── NotFoundException.java
    │   ├── RegistroDuplicadoException.java
    │   ├── UserNotAuthorizedException.java
    │   ├── ErroDTOs/
    │   │   ├── ErroCampo.java        # Field-level error record
    │   │   └── ErroResponse.java     # Envelope for all error responses
    │   └── handler/
    │       └── GlobalExceptionHandler.java  # @RestControllerAdvice
    ├── factories/
    │   ├── PostFactory.java          # Constructs Post from DTO + User
    │   └── CommentFactory.java       # Constructs Comment; assigns UUID id
    ├── mapper/
    │   ├── UserMapper.java           # MapStruct: User ↔ UserDTO / UserCommandDTO
    │   └── PostMapper.java           # MapStruct: Post ↔ PostDTO
    ├── model/
    │   ├── document/
    │   │   ├── User.java             # @Document(collection = "user")
    │   │   ├── Post.java             # @Document(collection = "post")
    │   │   └── Comment.java          # Embedded sub-document (no @Document)
    │   └── enums/
    │       └── RoleName.java
    ├── repository/
    │   ├── UserRepository.java       # MongoRepository<User, String>
    │   └── PostRepository.java       # MongoRepository<Post, String>
    └── resource/
        ├── GenericResource.java      # Default helper: createHeaderLocation()
        └── HomeResource.java         # GET / → 204
```

## Architecture Conventions

### CQRS split
Commands (create/update/delete) live in `command/`; queries (reads) live in `query/`. Keep them separate. Command services return domain objects; query services are `@Transactional(readOnly = true)`.

### DTOs
- **Input** (command side): validated Java `record` ending in `CommandDTO` or `DTO` (e.g. `UserCommandDTO`, `CommentDTO`)
- **Output** (query side): plain Java `record` living in `query/` (e.g. `UserDTO`, `PostDTO`)
- `AuthorDTO` is a denormalized snapshot embedded in `Post` and `Comment` — never a live reference

### Factories
Object construction that requires combining a DTO with a domain entity goes in a Factory (`PostFactory`, `CommentFactory`). Factories use static methods; the `@Component` annotation is present but unused.

### Mappers
All DTO ↔ entity conversions use MapStruct interfaces in `common/mapper/`. Never write manual mapping code.

### Repositories
One repository per aggregate root (`UserRepository`, `PostRepository`). Comments are not an aggregate root — they are embedded in `Post` and accessed through it.

### Exception handling
Throw domain exceptions (`NotFoundException`, `UserNotAuthorizedException`, `RegistroDuplicadoException`) from services. `GlobalExceptionHandler` translates them to `ErroResponse` JSON automatically. Never return raw error strings from controllers.

### Security
Authentication identity comes from `Authentication.getName()` (which is the user's email). Use `SecurityService.obterUsuario()` to resolve the full `User` entity from the security context.

### Ownership checks
Services are responsible for authorization. Before mutating a resource, verify `resource.getAuthor().id().equals(user.getId())` and throw `UserNotAuthorizedException` if the check fails.

### Pagination
All list endpoints accept Spring `Pageable` and return `PagedModel<DTO>` built from a `PageImpl`.
