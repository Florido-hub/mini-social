package com.florido.workshopmongo.command.post;

import com.florido.workshopmongo.common.model.document.Comment;
import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.resource.GenericResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostCommandResource implements GenericResource {

    private final PostCommandService postCommandService;

    @PostMapping
    public ResponseEntity<Void> createPost(
            @RequestBody @Valid PostCommandDTO dto,
            Authentication auth
    ) {
        Post post = postCommandService.createPost(dto, auth);

        URI location = createHeaderLocation(post.getId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Void> updatePost(
            @PathVariable String postId,
            @RequestBody PostCommandDTO dto,
            Authentication auth
    ){
        Post post = postCommandService.updatePost(postId, dto, auth);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable String postId,
            Authentication auth) {
        postCommandService.deletePost(postId, auth);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> createComment(
            @PathVariable String postId,
            @RequestBody @Valid CommentDTO dto){
        Comment comment = postCommandService.createComment(dto, postId);

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PatchMapping("/{postId}/comments")
    public ResponseEntity<Void> updateComment(
            @PathVariable String postId,
            @RequestBody CommentDTO dto
    ){
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{postId}/comments/{commentsId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable String postId,
            @PathVariable String commentsId
    ){
        postCommandService.deleteComment(postId, commentsId);

        return ResponseEntity.noContent().build();
    }
}
