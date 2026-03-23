package com.florido.workshopmongo.query.post;

import com.florido.workshopmongo.common.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostRepository postRepository;

}
