package com.florido.workshopmongo.common.exceptions.ErroDTOs;

public record ErroCampo(
        String campo,
        String erro
) {
}
