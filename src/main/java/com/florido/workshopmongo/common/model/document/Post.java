package com.florido.workshopmongo.common.model.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@Document(collection = "post")
public class Post implements Serializable {

    @Id
    private String id;
}
