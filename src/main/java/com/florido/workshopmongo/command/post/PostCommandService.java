package com.florido.workshopmongo.command.post;

import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostRepository postRepository;

    public Post create(Post post){
        return postRepository.save(post);
    }
}
