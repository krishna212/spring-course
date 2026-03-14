package com.krishnamullangi.spring.course.Repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import com.krishnamullangi.spring.course.model.Content;

import jakarta.annotation.PostConstruct;

import java.util.Optional;

@Repository
// @Repository tells Spring: make this class an app object I can inject elsewhere.
public class ContentCollectionRepository {
	// In-memory list. For now data lives only while app is running.
    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {
    }
    
	// Return all content items.
    public List<Content> findAll() {
        return contentList;
    }

	// Find one item by id.
    public Optional<Content> findById(Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    @PostConstruct
    private void init() {
        contentList.add(new Content(1, "Spring Boot Tutorial", "Learn Spring Boot in 10 minutes", "PUBLISHED", com.krishnamullangi.spring.course.model.Type.ARTICLE, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://www.youtube.com/watch?v=vtPkZShrvXQ"));
        contentList.add(new Content(2, "REST API Design", "Best practices for designing REST APIs", "IN_PROGRESS", com.krishnamullangi.spring.course.model.Type.VIDEO, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://www.youtube.com/watch?v=Q-BpqyOT3a8"));
    }

    public void save(Content content){
        contentList.add(content);
    }
    public boolean existsById(Integer id){
        return contentList.stream().anyMatch(c -> c.id().equals(id));
    }
    public void update(Content content){
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

}
