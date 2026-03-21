package com.florido.workshopmongo.common.mapper;

import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.query.user.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);

    User toModel(UserDTO user);
}
