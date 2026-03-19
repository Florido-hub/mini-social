package com.florido.workshopmongo.query;

import com.florido.workshopmongo.domain.User;
import com.florido.workshopmongo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserQueryService {

    private final UserRepository userRepository;

    public UserQueryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> findAll() {
        return Page.empty();
    }

    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
