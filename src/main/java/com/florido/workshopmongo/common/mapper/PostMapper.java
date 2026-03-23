package com.florido.workshopmongo.common.mapper;

import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.query.post.PostDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDTO toDto(Post post);

    Post toModel(PostDTO postDTO);
}
