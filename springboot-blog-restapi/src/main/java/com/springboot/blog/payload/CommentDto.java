package com.springboot.blog.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Api(value = "Comment model information")
public class CommentDto {
    @ApiModelProperty(value = "Post comment ID")
    private long id;

    @NotEmpty(message = "Name field should not be empty")
    @Size(min = 5, message = "Comment must have at least 5 characters")
    @ApiModelProperty(value = "Comment name")
    private String name;

    @NotEmpty(message = "E-mail should not be empty")
    @Email(message = "Not valid e-mail")
    @ApiModelProperty(value = "Comment email")
    private String email;

    @NotEmpty(message = "Comment should not be empty")
    @Size(min = 10, message = "Comment must have at least 10 characters")
    @ApiModelProperty(value = "Comment body")
    private String commentBody;
}
