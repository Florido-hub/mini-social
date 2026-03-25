package com.florido.workshopmongo.query.post;

import com.florido.workshopmongo.common.mapper.PostMapper;
import com.florido.workshopmongo.common.model.document.Post;
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
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostQueryResource {

    private final PostQueryService postQueryService;
    private final PostMapper postMapper;

    @GetMapping
    public ResponseEntity<PagedModel<PostDTO>> all(Pageable pageRequest) {
        Page<Post> page = postQueryService.findAll(pageRequest);

        List<PostDTO> postDto = page.getContent().stream().map(postMapper::toDto).toList();

        Page<PostDTO> pageDto = new PageImpl<>(postDto, page.getPageable(), page.getTotalElements());

        PagedModel<PostDTO> pagedModel = new PagedModel<>(pageDto);

        return ResponseEntity.ok().body(pagedModel);
    }

    @GetMapping("/id/{postId}")
    public ResponseEntity<PostDTO> getById(@PathVariable String postId) {
        Optional<Post> postOpt = postQueryService.findById(postId);

        if (postOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Post post = postOpt.get();

        PostDTO postDto = postMapper.toDto(post);

        return ResponseEntity.ok(postDto);
    }
}
