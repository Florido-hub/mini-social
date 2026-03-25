package com.florido.workshopmongo.command.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCommandDTO(

        @NotBlank(message = "Campo obrigatorio")
        String name,

        @NotBlank(message = "Campo obrigatorio")
        @Email(message = "Insira um email válido")
        String email,

        @NotBlank(message = "Campo obrigatorio")
        String password
) {
}
