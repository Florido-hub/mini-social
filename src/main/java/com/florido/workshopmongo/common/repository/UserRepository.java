package com.florido.workshopmongo.common.repository;

import com.florido.workshopmongo.common.model.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByName(String username);

    Optional<User> findByEmail(String email);

    void deleteUserByName(String name);
}
