package com.florido.workshopmongo.query;

import com.florido.workshopmongo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserQueryResource {

    private final UserQueryService userQueryService;

    public UserQueryResource(final UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping
    public ResponseEntity<PagedModel<UserDTO>> all() {
        Page<User> page = userQueryService.findAll();

        List<UserDTO> usersDto = new ArrayList<>();
        for (User u : page.getContent()) {
            UserDTO userDTO = UserMapper.to(u);
            usersDto.add(userDTO);
        }

        Page<UserDTO> pageDto = new PageImpl<>(usersDto, page.getPageable(), page.getTotalPages());

        PagedModel<UserDTO> pagedModel = new PagedModel<>(pageDto);

        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> one(@PathVariable UUID id) {
        Optional<User> userOpt = userQueryService.findById(id.toString());

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();

        UserDTO userDTO = UserMapper.to(user);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> one(@PathVariable String username) {
        Optional<User> userOpt = userQueryService.findByUsername(username);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();

        UserDTO userDTO = UserMapper.to(user);

        return ResponseEntity.ok(userDTO);
    }
}
