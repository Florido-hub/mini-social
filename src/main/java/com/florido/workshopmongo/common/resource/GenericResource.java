package com.florido.workshopmongo.common.resource;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface GenericResource {

    default URI createHeaderLocation(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    default URI createHeaderLocation(String id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
