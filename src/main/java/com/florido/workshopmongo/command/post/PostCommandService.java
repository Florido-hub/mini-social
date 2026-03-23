package com.florido.workshopmongo.command.post;

import com.florido.workshopmongo.common.factories.CommentFactory;
import com.florido.workshopmongo.common.factories.PostFactory;
import com.florido.workshopmongo.common.model.document.Comment;
import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.common.repository.PostRepository;
import com.florido.workshopmongo.common.repository.UserRepository;
import com.florido.workshopmongo.query.post.AuthorDTO;
import com.florido.workshopmongo.query.post.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPost(PostCommandDTO dto) {

        User user = userRepository.findById(dto.idAuthor())
                .orElseThrow();

        Post post = PostFactory.create(dto, user);

        user.getPosts().add(post);

        userRepository.save(user);

        return postRepository.save(post);
    }

    public Comment createComment(CommentDTO dto, String postId){
        Post post = postRepository.findById(postId)
                .orElseThrow();

        User user = userRepository.findById(dto.idAuthor())
                .orElseThrow();

        Comment comment = CommentFactory.create(dto, user);

        post.getComments().add(comment);
        postRepository.save(post);

        return comment;
    }
}
