package com.florido.workshopmongo.command.post;

import jakarta.validation.constraints.NotBlank;

public record CommentDTO(

        @NotBlank(message = "Campo obrigatorio")
        String text,

        @NotBlank(message = "Campo obrigatorio")
        String idAuthor
) {
}
