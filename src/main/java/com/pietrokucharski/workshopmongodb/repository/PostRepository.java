package com.pietrokucharski.workshopmongodb.repository;

import com.pietrokucharski.workshopmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
