package com.florido.workshopmongo.command.post;

import com.florido.workshopmongo.query.post.AuthorDTO;

public record CommentCommandDto(
        String text,
        AuthorDTO author
) {
}
