package com.florido.workshopmongo.common.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    ResponseEntity<Void> home() {
        return ResponseEntity.noContent().build();
    }
}