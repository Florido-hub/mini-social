package com.florido.workshopmongo.common.repository;

import com.florido.workshopmongo.common.model.document.Post;
import com.florido.workshopmongo.query.post.AuthorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByAuthor(AuthorDTO author);
    
    Page<Post> findByDate(Date date, Pageable pageable);
}
