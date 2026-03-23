package com.florido.workshopmongo.query.post;

import java.util.Date;

public record PostDTO(
        String id,
        Date date,
        String title,
        String body,
        AuthorDTO author
) {
}
