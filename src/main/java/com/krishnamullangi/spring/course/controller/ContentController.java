package com.krishnamullangi.spring.course.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.krishnamullangi.spring.course.Repository.ContentCollectionRepository;
import com.krishnamullangi.spring.course.model.Content;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({ "/api/content", "/content" })
public class ContentController {
    // Box to store the repo object for this class.
    private final ContentCollectionRepository repository;

    // Constructor = tool that fills the box when object is created.
    public ContentController(ContentCollectionRepository contentCollectionRepository) {
        this.repository = contentCollectionRepository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    // @PathVariable = Spring version of Express req.params.
    // Example: /api/content/2 -> id becomes 2.
    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        // orElseThrow wants a throwable object builder, not text.
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found with id: " + id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping({"/api/create", "/create"})
    public void createContent(@RequestBody Content content) {
        repository.save(content);    
} 
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id){
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found with id: " + id);
        }
        repository.update(content);
    } 
}
