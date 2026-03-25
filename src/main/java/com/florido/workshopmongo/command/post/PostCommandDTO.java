package com.florido.workshopmongo.command.post;

import jakarta.validation.constraints.NotBlank;

public record PostCommandDTO(

        @NotBlank(message = "Campo obrigatorio")
        String title,

        @NotBlank(message = "Campo obrigatorio")
        String body
) {
}
