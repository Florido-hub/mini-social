package com.florido.workshopmongo.common.model.document;

import com.florido.workshopmongo.query.post.AuthorDTO;

import java.util.Date;

public record Comment(
        String text,
        Date date,
        AuthorDTO author
) {
}
