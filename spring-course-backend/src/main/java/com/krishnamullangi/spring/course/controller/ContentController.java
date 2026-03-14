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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
// Base route box. Methods below add sub-routes on top of this.
@RequestMapping({ "/api/content", "/content" })
// @CrossOrigin = allow frontend (different port/origin) to call this API.
@CrossOrigin
public class ContentController {
    // Controller = API route handler (mediator). It receives request and calls data/logic layer.
    // Right now it calls repository directly; later best practice is call Service first.
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

    // @PostMapping = runs when client sends POST request (create action from frontend).
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping({"", "/"})
    public void createContent(@RequestBody Content content) {
        repository.save(content);    
} 

    // PUT = update existing item (replace old item with same id).
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id){
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found with id: " + id);
        }
        repository.update(content);
    } 

    // DELETE = remove item by id.
    // @ResponseStatus(NO_CONTENT) = success with no response body.
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        if(repository.existsById(id)){
         repository.deleteById(id);   
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No event by  ID" + id);
        }
    }
}