package com.florido.workshopmongo.query.post;

import java.util.Date;

public record PostDTO(
        Date date,
        String title,
        String body
) {
}
