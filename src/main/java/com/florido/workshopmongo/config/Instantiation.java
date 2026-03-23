package com.florido.workshopmongo.config;

import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.common.repository.PostRepository;
import com.florido.workshopmongo.common.repository.UserRepository;
import com.florido.workshopmongo.query.post.AuthorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
@RequiredArgsConstructor
public class Instantiation implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User florido = new User(null,"florido", "florido@gmail.com");
        User leo = new User(null, "leo", "leo@gmail.com");
        User seneca = new User(null, "seneca", "seneca@gmail.com");

        userRepository.save(florido);
        userRepository.save(leo);
        userRepository.save(seneca);

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo", new AuthorDTO(florido));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(leo));

        postRepository.save(post1);
        postRepository.save(post2);

        florido.getPosts().add(post1);
        userRepository.save(florido);

        leo.getPosts().add(post2);
        userRepository.save(leo);


    }
}
