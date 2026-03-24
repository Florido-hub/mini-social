package com.florido.workshopmongo.command.post;

import com.florido.workshopmongo.common.mapper.PostMapper;
import com.florido.workshopmongo.common.model.document.Comment;
import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.resource.GenericResource;
import com.florido.workshopmongo.query.post.PostDTO;
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

    @PostMapping()
    public ResponseEntity<PostDTO> createPost(
            @RequestBody PostCommandDTO dto) {
        Post post = postCommandService.createPost(dto);

        return ResponseEntity.created(URI.create(post.getId())).body(postMapper.toDto(post));
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> createComment(
            @PathVariable("postId") String postId,
            @RequestBody CommentDTO dto){
        Comment comment = postCommandService.createComment(dto, postId);

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }
}
