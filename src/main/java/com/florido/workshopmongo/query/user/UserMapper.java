package com.florido.workshopmongo.query.user;

import com.florido.workshopmongo.common.model.document.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO to(User user);

    User to(UserDTO user);
}
