package com.florido.workshopmongo.common.repository;

import com.florido.workshopmongo.common.model.document.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
