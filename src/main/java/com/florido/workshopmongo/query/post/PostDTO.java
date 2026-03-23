package com.florido.workshopmongo.query.post;

import com.florido.workshopmongo.common.model.document.Comment;

import java.util.Date;
import java.util.List;

public record PostDTO(
        String id,
        Date date,
        String title,
        String body,
        AuthorDTO author,
        List<Comment> comments
) {
}
