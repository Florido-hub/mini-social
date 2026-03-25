package com.florido.workshopmongo.command.user;

public record UserCommandDTO(
        String name,
        String email,
        String password
) {
}
