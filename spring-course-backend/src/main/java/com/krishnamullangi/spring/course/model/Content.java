package com.krishnamullangi.spring.course.model;
import java.time.LocalDateTime;

// Record = short way to make a data class.
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
