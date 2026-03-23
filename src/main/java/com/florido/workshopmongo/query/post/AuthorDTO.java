package com.florido.workshopmongo.query.post;

import com.florido.workshopmongo.common.model.document.User;

public record AuthorDTO(
        String id, String name
) {
    public AuthorDTO (User user) {
        this(user.getId(), user.getName());
    }
}
