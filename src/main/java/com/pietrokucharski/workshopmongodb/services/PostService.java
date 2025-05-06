package com.pietrokucharski.workshopmongodb.services;

import com.pietrokucharski.workshopmongodb.domain.Post;
import com.pietrokucharski.workshopmongodb.domain.User;
import com.pietrokucharski.workshopmongodb.repository.PostRepository;
import com.pietrokucharski.workshopmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Obejct not found"));
    }

    public List<Post> findByTitle(String text) {
        return repository.findByTitleContainingIgnoreCase(text);
    }
}
