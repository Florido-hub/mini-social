package com.florido.workshopmongo.common.mapper;

import com.florido.workshopmongo.command.post.CommentCommandDto;
import com.florido.workshopmongo.common.model.document.Comment;
import com.florido.workshopmongo.common.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CommandMapper {

    @Autowired
    protected UserRepository userRepository;

    @Mapping(target = "author", expression = "java(new com.florido.workshopmongo.query.post.AuthorDTO(userRepository.findById(dto.idAuthor()).orElse(null)))")
    public abstract Comment toEntity(CommentCommandDto dto);
}
