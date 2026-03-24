package com.florido.workshopmongo.common.model.document;

import com.florido.workshopmongo.query.post.AuthorDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class Comment{

    private String id;
    private String text;
    private Date date;
    private AuthorDTO author;

    public Comment(String text, Date date, AuthorDTO author) {
        this.text = text;
        this.date = date;
        this.author = author;
    }
}
