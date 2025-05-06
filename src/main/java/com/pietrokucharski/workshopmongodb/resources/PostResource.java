package com.pietrokucharski.workshopmongodb.resources;

import com.pietrokucharski.workshopmongodb.domain.Post;
import com.pietrokucharski.workshopmongodb.domain.User;
import com.pietrokucharski.workshopmongodb.dto.UserDTO;
import com.pietrokucharski.workshopmongodb.resources.util.URL;
import com.pietrokucharski.workshopmongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }
}
