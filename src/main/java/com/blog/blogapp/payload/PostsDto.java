package com.blog.blogapp.payload;

import lombok.Data;

@Data
public class PostsDto {
    private Long id;
    private String title;
    private String description;
    private String content;
}

