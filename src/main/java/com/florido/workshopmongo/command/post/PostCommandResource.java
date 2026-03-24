package com.florido.workshopmongo.command.post;

import com.florido.workshopmongo.common.mapper.PostMapper;
import com.florido.workshopmongo.common.model.document.Comment;
import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.resource.GenericResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostCommandResource implements GenericResource {

    private final PostCommandService postCommandService;
    private final PostMapper postMapper;

    @PostMapping
    public ResponseEntity<Void> createPost(
            @RequestBody @Valid PostCommandDTO dto) {
        Post post = postCommandService.createPost(dto);

        URI location = createHeaderLocation(post.getId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Void> updatePost(
            @PathVariable String postId,
            @RequestBody PostCommandDTO dto
    ){
        Post post = postCommandService.updatePost(postId, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}/{userId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable String postId,
            @PathVariable String userId) {
        postCommandService.deletePost(postId, userId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> createComment(
            @PathVariable String postId,
            @RequestBody @Valid CommentDTO dto){
        Comment comment = postCommandService.createComment(dto, postId);

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }
}
