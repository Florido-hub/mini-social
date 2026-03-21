package com.florido.workshopmongo.command.post;

import com.florido.workshopmongo.common.resource.GenericResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostCommandResource implements GenericResource {

    private final PostCommandService postCommandService;
}
