package com.florido.workshopmongo.common.mapper;

import com.florido.workshopmongo.common.model.document.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommandMapper {

    Comment toDto(Comment comment);
}
