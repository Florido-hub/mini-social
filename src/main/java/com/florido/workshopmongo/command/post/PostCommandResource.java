package com.florido.workshopmongo.command.post;

import com.florido.workshopmongo.command.user.UserCommandService;
import com.florido.workshopmongo.common.mapper.PostMapper;
import com.florido.workshopmongo.common.mapper.UserMapper;
import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.common.resource.GenericResource;
import com.florido.workshopmongo.query.post.AuthorDTO;
import com.florido.workshopmongo.query.post.PostDTO;
import com.florido.workshopmongo.query.user.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostCommandResource implements GenericResource {

    private final PostCommandService postCommandService;
    private final PostMapper postMapper;

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;
    private final UserMapper userMapper;

    @PostMapping("/{id}")
    public ResponseEntity<PostDTO> createPost(
            @PathVariable String id,
            @RequestBody PostCommandDTO dto) {

        Optional<User> userOpt = userQueryService.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();

        Post post = new Post();

        post.setDate(new Date());
        post.setTitle(dto.title());
        post.setBody(dto.body());
        post.setAuthor(new AuthorDTO(user));

        user.getPosts().add(post);

        postCommandService.create(post);

        userCommandService.update(id, userMapper.toDto(user));

        PostDTO response = postMapper.toDto(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
