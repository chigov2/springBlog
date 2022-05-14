package com.springboot.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {
    private long id;
    //title should not be null or empty
    //title should have at least 2 characters
    @NotEmpty(message = "Must not be empty")
    @Size(min = 2, message = "Title must have more then 2 characters")
    private String title;

    //must be not null or empty
    //should have at least 10 characters
    @NotEmpty(message = "Must not be empty")
    @Size(min = 10, message = "Description must have at least 10 characters")
    private String description;

    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}
