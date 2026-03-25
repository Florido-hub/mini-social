package com.florido.workshopmongo.command.post;

import com.florido.workshopmongo.common.exceptions.NotFoundException;
import com.florido.workshopmongo.common.exceptions.UserNotAuthorizedException;
import com.florido.workshopmongo.common.factories.CommentFactory;
import com.florido.workshopmongo.common.factories.PostFactory;
import com.florido.workshopmongo.common.model.document.Comment;
import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.common.repository.PostRepository;
import com.florido.workshopmongo.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPost(PostCommandDTO dto, Authentication auth) {
        User user = userRepository.findByName(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = PostFactory.create(dto, user);
        Post save = postRepository.save(post);

        user.getPosts().add(save);
        userRepository.save(user);

        return save;
    }

    public Post updatePost(String id, PostCommandDTO dto, Authentication auth) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post não  encontrado"));

        User user = userRepository.findByName(auth.getName()).orElseThrow(() -> new NotFoundException("User Not Found"));

        if (!post.getAuthor().id().equals(user.getId())) {
            throw new UserNotAuthorizedException("Usuário não autorizado a editar este post");
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

    public void deletePost(String postId, Authentication auth) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));
        User user = userRepository.findByName(auth.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (!post.getAuthor().id().equals(user.getId())) {
            throw new UserNotAuthorizedException("Você n pode excluir o post de outra pessoa, SEU BABACA!");
        }
        user.getPosts().remove(post);
        userRepository.save(user);

        postRepository.deleteById(postId);
    }

    public Comment createComment(CommentDTO dto, String postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));

        User user = userRepository.findById(dto.idAuthor())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Comment comment = CommentFactory.create(dto, user);

        post.getComments().add(comment);
        postRepository.save(post);

        return comment;
    }

    public void deleteComment(String postId, String commentsId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));

        post.getComments().removeIf(comment -> comment.getId().equals(commentsId));

        postRepository.save(post);
    }
}
