package com.florido.workshopmongo.command.user;

import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.query.user.UserDTO;
import com.florido.workshopmongo.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    // TODO: merge patch (RFC 7386)
    public User update(String id, UserDTO patch) {
        if (id.isBlank()) {
            throw new RuntimeException("user with id " + id + " not found");
        }

        return new User();
    }

    public User delete(User user) {
        return deleteById(user.getId());
    }

    public User deleteById(String id) {
        if (id.isBlank()) {
            throw new RuntimeException("id can't be blank");
        }

        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("user with id " + id + " not found");
        }

        User user = userOpt.get();

        return user;
    }

    public User deleteByUsername(String username) {
        if (username.isBlank()) {
            throw new RuntimeException("username can't be blank");
        }

        Optional<User> userOpt = userRepository.findByName(username);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("user with username " + username + " not found");
        }

        User user = userOpt.get();

        return user;
    }

    @Deprecated
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
