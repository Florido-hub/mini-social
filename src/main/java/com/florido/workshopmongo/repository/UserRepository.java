package com.florido.workshopmongo.repository;

import com.florido.workshopmongo.domain.User;
import com.florido.workshopmongo.query.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByName(String username);

    Optional<User> findByEmail(String email);
}
