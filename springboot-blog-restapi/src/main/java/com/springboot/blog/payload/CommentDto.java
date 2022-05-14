package com.springboot.blog.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private long id;

    @NotEmpty(message = "Name field should not be empty")
    @Size(min = 5, message = "Comment must have at least 5 characters")
    private String name;

    @NotEmpty(message = "E-mail should not be empty")
    @Email(message = "Not valid e-mail")
    private String email;

    @NotEmpty(message = "Comment should not be empty")
    @Size(min = 10, message = "Comment must have at least 10 characters")
    private String commentBody;
}
