package com.florido.workshopmongo.common.factories;

import com.florido.workshopmongo.command.post.PostCommandDTO;
import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.query.post.AuthorDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PostFactory {

    public static Post create(PostCommandDTO dto, User user) {
        Post post = new Post();
        post.setId(null);
        post.setDate(new Date());
        post.setTitle(dto.title());
        post.setBody(dto.body());
        post.setAuthor(new AuthorDTO(user));
        return post;
    }
}
