package com.florido.workshopmongo.resources;

import com.florido.workshopmongo.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {


    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        User florido = new User("f1b563cc-c20e-42fc-b28b-7c2cae679ed3", "Flórido", "florido@gmail.com");
        User alex = new User("24e167d6-2cbe-4cba-ae8a-aebca2f4114a", "Alex", "alex@gmail.com");
        List<User> users = List.of(florido, alex);
        return ResponseEntity.ok().body(users);
    }
}
