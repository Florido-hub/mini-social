package com.florido.workshopmongo.command.user;

import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.query.user.UserDTO;
import com.florido.workshopmongo.common.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserCommandResource {

    private final UserCommandService userCommandService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDTO userDTO) {
        User user = userMapper.toModel(userDTO);

        User userCreated = userCommandService.create(user);
        String id = userCreated.getId();

        return ResponseEntity.created(URI.create(id)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> patch(@PathVariable String id, UserDTO patch) {
        User updated = userCommandService.update(id, patch);

        UserDTO responseBody = userMapper.toDto(updated);

        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable String id, @RequestParam boolean confirm) {
        if (!confirm) {
            HttpStatusCode status = HttpStatusCode.valueOf(400);
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, "Confirmation is required to delete user.");

            return ResponseEntity.of(problemDetail).build();
        }

        User deleted = userCommandService.deleteById(id);

        UserDTO responseBody = userMapper.toDto(deleted);

        return ResponseEntity.ok(responseBody);
    }
}
