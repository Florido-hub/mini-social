package com.florido.workshopmongo.command.post;

import com.florido.workshopmongo.common.factories.CommentFactory;
import com.florido.workshopmongo.common.factories.PostFactory;
import com.florido.workshopmongo.common.model.document.Comment;
import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.common.repository.PostRepository;
import com.florido.workshopmongo.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    public void deletePost(String postId, String userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow();
        User user = userRepository.findById(userId)
                .orElseThrow();

        user.getPosts().remove(post);
        userRepository.save(user);

        postRepository.deleteById(postId);
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
