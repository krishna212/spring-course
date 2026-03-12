package com.krishnamullangi.spring.course.model;
import java.time.LocalDateTime;

public record Content(
    Integer id,
    String title, 
    String desc,
    String status,
    Type contentType,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated,
    String url
) {

}
