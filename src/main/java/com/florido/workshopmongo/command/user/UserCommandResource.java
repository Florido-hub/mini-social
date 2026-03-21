package com.florido.workshopmongo.command.user;

import com.florido.workshopmongo.domain.User;
import com.florido.workshopmongo.query.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserCommandResource {

    private final UserCommandService userCommandService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDTO userDTO) {
        return ResponseEntity.created(URI.create("")).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(@PathVariable String id, UserDTO patch) {
        return ResponseEntity.created(URI.create("")).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable String id) {
        return ResponseEntity.ok(new User());
    }
}
