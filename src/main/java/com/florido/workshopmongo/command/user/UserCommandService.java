package com.florido.workshopmongo.command.user;

import com.florido.workshopmongo.common.exceptions.NotFoundException;
import com.florido.workshopmongo.common.exceptions.RegistroDuplicadoException;
import com.florido.workshopmongo.common.exceptions.UserNotAuthorizedException;
import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.common.repository.PostRepository;
import com.florido.workshopmongo.common.repository.UserRepository;
import com.florido.workshopmongo.query.post.AuthorDTO;
import com.florido.workshopmongo.query.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final PostRepository postRepository;

    public User create(User user) {
        if(userRepository.existsByEmailOrName(user.getEmail(),user.getName())){
            throw new RegistroDuplicadoException("nome de usuário ou email já estão em uso");
        }
        String password = user.getPassword();

        user.setPassword(encoder.encode(password));
        return userRepository.save(user);
    }
    
    public User update(UserDTO patch, Authentication auth) {
        Objects.requireNonNull(userRepository.findByEmail(auth.getName()), "id can't be blank");

        User user = userRepository.findByEmail(auth.getName()).orElseThrow(() -> new NotFoundException("user not found"));

        User merged = merge(user, patch);
        userRepository.save(merged);

        return merged;
    }

    private User merge(User user, UserDTO patch) {
        User merged = new User();
        merged.setId(user.getId());

        if (patch.name() != null) {
            merged.setName(patch.name());
        }

        if (patch.email() != null) {
            merged.setEmail(patch.email());
        }

        return merged;
    }

    public User delete(User user) {
        return deleteById(user.getId());
    }

    public User deleteById(String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!user.getEmail().equals(loggedUsername)) {
            throw new UserNotAuthorizedException("Você não pode deletar a conta de outra pessoa");
        }

        List<Post> posts = postRepository.findByAuthor(new AuthorDTO(user));
        postRepository.deleteAll(posts);

        userRepository.delete(user);

        return user;
    }

    @Deprecated
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
