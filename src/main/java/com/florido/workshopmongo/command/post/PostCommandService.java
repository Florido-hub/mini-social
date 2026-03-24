package com.florido.workshopmongo.command.post;

import com.florido.workshopmongo.common.factories.CommentFactory;
import com.florido.workshopmongo.common.factories.PostFactory;
import com.florido.workshopmongo.common.model.document.Comment;
import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.common.repository.PostRepository;
import com.florido.workshopmongo.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPost(PostCommandDTO dto) {

        User user = userRepository.findById(dto.idAuthor())
                .orElseThrow();

        Post post = PostFactory.create(dto, user);
        Post save = postRepository.save(post);

        user.getPosts().add(save);
        userRepository.save(user);

        return save;
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
