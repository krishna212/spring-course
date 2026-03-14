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

    // @PostConstruct = runs once when app starts (startup hook), not an API route.
    @PostConstruct
    private void init() {
        contentList.add(new Content(1, "Spring Boot Tutorial", "Learn Spring Boot in 10 minutes", "PUBLISHED", com.krishnamullangi.spring.course.model.Type.ARTICLE, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://www.youtube.com/watch?v=vtPkZShrvXQ"));
        contentList.add(new Content(2, "REST API Design", "Best practices for designing REST APIs", "IN_PROGRESS", com.krishnamullangi.spring.course.model.Type.VIDEO, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://www.youtube.com/watch?v=Q-BpqyOT3a8"));
        contentList.add(new Content(3, "Java Streams Crash Course", "Filter, map, reduce with practical examples", "PUBLISHED", com.krishnamullangi.spring.course.model.Type.VIDEO, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://www.youtube.com/watch?v=3m6X8sWfT2Q"));
        contentList.add(new Content(4, "Spring Data JPA Basics", "How entities and repositories work", "COMPLETED", com.krishnamullangi.spring.course.model.Type.ARTICLE, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://spring.io/projects/spring-data-jpa"));
        contentList.add(new Content(5, "Docker for Spring Apps", "Containerize and run Spring Boot services", "IN_PROGRESS", com.krishnamullangi.spring.course.model.Type.COURSE, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://www.docker.com/"));
        contentList.add(new Content(6, "Testing Controllers with MockMvc", "Write focused API tests quickly", "IDEA", com.krishnamullangi.spring.course.model.Type.ARTICLE, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://docs.spring.io/spring-framework/reference/testing/mockmvc.html"));
        contentList.add(new Content(7, "React + Spring Fullstack", "Connect Vite frontend with Spring backend", "PUBLISHED", com.krishnamullangi.spring.course.model.Type.COURSE, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://spring.io/guides/tutorials/react-and-spring-data-rest"));
        contentList.add(new Content(8, "Build a Todo API", "CRUD endpoints with validation", "COMPLETED", com.krishnamullangi.spring.course.model.Type.VIDEO, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://www.youtube.com/watch?v=9SGDpanrc8U"));
        contentList.add(new Content(9, "Caching in Spring Boot", "Use cache abstraction for faster responses", "IDEA", com.krishnamullangi.spring.course.model.Type.CONFERENCE_TALK, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://spring.io/guides/gs/caching"));
        contentList.add(new Content(10, "Security with JWT", "Protect API routes with token auth", "IN_PROGRESS", com.krishnamullangi.spring.course.model.Type.COURSE, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://jwt.io/introduction"));
        contentList.add(new Content(11, "Exception Handling Patterns", "Use @ControllerAdvice for clean error responses", "PUBLISHED", com.krishnamullangi.spring.course.model.Type.ARTICLE, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://www.baeldung.com/exception-handling-for-rest-with-spring"));
        contentList.add(new Content(12, "Actuator Monitoring", "Expose health and metrics endpoints", "COMPLETED", com.krishnamullangi.spring.course.model.Type.CONFERENCE_TALK, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://docs.spring.io/spring-boot/reference/actuator/index.html"));
        contentList.add(new Content(13, "Profiles and Environments", "Manage dev/prod configs with profiles", "IN_PROGRESS", com.krishnamullangi.spring.course.model.Type.ARTICLE, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://docs.spring.io/spring-boot/reference/features/profiles.html"));
        contentList.add(new Content(14, "Deploy Spring Boot to Cloud", "Package and deploy to a cloud platform", "IDEA", com.krishnamullangi.spring.course.model.Type.VIDEO, java.time.LocalDateTime.now(), java.time.LocalDateTime.now(), "https://www.youtube.com/watch?v=9iGXr_4x9f8"));
    }

    public void save(Content content){
        contentList.add(content);
    }
    public boolean existsById(Integer id){
        return contentList.stream().anyMatch(c -> c.id().equals(id));
    }
    public void update(Content content){
        // removeIf = remove old item with same id, then add updated item.
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }
    public void deleteById(Integer id){
        contentList.removeIf(c -> c.id().equals(id));
    }

}
