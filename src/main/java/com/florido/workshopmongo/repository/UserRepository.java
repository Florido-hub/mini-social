package com.florido.workshopmongo.repository;

import com.florido.workshopmongo.domain.User;
import com.florido.workshopmongo.query.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

    User findByName(String username);

    User findByEmail(String email);
}
