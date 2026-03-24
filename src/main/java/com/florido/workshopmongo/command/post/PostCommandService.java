package com.florido.workshopmongo.command.post;

import com.florido.workshopmongo.common.factories.CommentFactory;
import com.florido.workshopmongo.common.factories.PostFactory;
import com.florido.workshopmongo.common.model.document.Comment;
import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.common.repository.PostRepository;
import com.florido.workshopmongo.common.repository.UserRepository;
import com.florido.workshopmongo.query.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPost(PostCommandDTO dto) {
        User user = userRepository.findById(dto.idAuthor())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Post post = PostFactory.create(dto, user);
        Post save = postRepository.save(post);

        user.getPosts().add(save);
        userRepository.save(user);

        return save;
    }

    public Post updatePost(String id, PostCommandDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getAuthor().id().equals(dto.idAuthor())) {
            throw new RuntimeException("Usuário não autorizado a editar este post");
        }
        if (dto.title() != null) {
            post.setTitle(dto.title());
        }
        if (dto.body() != null) {
            post.setBody(dto.body());
        }
        post.setDate(new Date());

        postRepository.save(post);
        return post;
    }

    public void deletePost(String postId, String userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.getPosts().remove(post);
        userRepository.save(user);

        postRepository.deleteById(postId);
    }

    public Comment createComment(CommentDTO dto, String postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findById(dto.idAuthor())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = CommentFactory.create(dto, user);

        post.getComments().add(comment);
        postRepository.save(post);

        return comment;
    }
}
