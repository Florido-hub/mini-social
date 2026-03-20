package com.florido.workshopmongo.query;

import com.florido.workshopmongo.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO to(User user);

    User to(UserDTO user);
}
