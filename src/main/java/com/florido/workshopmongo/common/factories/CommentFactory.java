package com.florido.workshopmongo.common.factories;

import com.florido.workshopmongo.command.post.CommentDTO;
import com.florido.workshopmongo.common.model.document.Comment;
import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.query.post.AuthorDTO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class CommentFactory {

    public static Comment create(CommentDTO dto, User user) {
        Comment comment = new Comment();
        comment.setId(UUID.randomUUID().toString());
        comment.setText(dto.text());
        comment.setDate(new Date());
        comment.setAuthor(new AuthorDTO(user));
        return comment;
    }
}
