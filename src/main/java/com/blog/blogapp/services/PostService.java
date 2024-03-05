package com.blog.blogapp.services;


import java.util.List;

import com.blog.blogapp.payload.PostsDto;


public interface PostService {
    PostsDto createPost(PostsDto postsdto);

    List<PostsDto> getAllPosts();

    PostsDto getPostById(Long id);

    PostsDto updatePost(PostsDto postsDto ,Long id);

    void deletePostById(Long id);

}
