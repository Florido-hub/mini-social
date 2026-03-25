package com.florido.workshopmongo.query.user;

import com.florido.workshopmongo.common.exceptions.NotFoundException;
import com.florido.workshopmongo.common.mapper.PostMapper;
import com.florido.workshopmongo.common.mapper.UserMapper;
import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.query.post.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserQueryResource {

    private final UserQueryService userQueryService;
    private final UserMapper userMapper;
    private final PostMapper postMapper;

    @GetMapping
    public ResponseEntity<PagedModel<UserDTO>> all(Pageable pageRequest) {
        Page<User> page = userQueryService.findAll(pageRequest);

        List<UserDTO> usersDto = page.getContent().stream().map(userMapper::toDto).toList();

        Page<UserDTO> pageDto = new PageImpl<>(usersDto, page.getPageable(), page.getTotalElements());

        PagedModel<UserDTO> pagedModel = new PagedModel<>(pageDto);

        return ResponseEntity.ok().body(pagedModel);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable String id) {
        Optional<User> userOpt = userQueryService.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();

        UserDTO userDTO = userMapper.toDto(user);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getByUsername(@PathVariable String username) {
        User user = userQueryService.findByUsername(username).orElseThrow(()-> new NotFoundException("User not found"));;

        UserDTO userDTO = userMapper.toDto(user);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<PagedModel<PostDTO>> allPosts(@PathVariable String id, Pageable pageRequest) {
        Optional<User> userOpt = userQueryService.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Post> posts = userOpt.get().getPosts();

        List<PostDTO> postDto = posts
                .stream()
                .map(postMapper::toDto)
                .toList();

        Page<PostDTO> pageDto = new PageImpl<>(postDto, pageRequest, posts.size());

        PagedModel<PostDTO> pagedModel = new PagedModel<>(pageDto);

        return ResponseEntity.ok(pagedModel);
    }
}
