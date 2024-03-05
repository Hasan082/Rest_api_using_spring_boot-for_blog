package com.blog.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.model.Post;
import com.blog.blogapp.payload.PostsDto;
import com.blog.blogapp.repository.postRepository;
import com.blog.blogapp.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    private postRepository postRepo;

    public PostServiceImpl(postRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostsDto createPost(PostsDto postsDto) {
        //Convert DTO to Model

        Post post = mapToEntity(postsDto);
        Post newPost = postRepo.save(post);

        //Converet model to Dto

        PostsDto postResposnse = mapToDto(newPost);

        return postResposnse;
    }

    @Override
    public List<PostsDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }


    private PostsDto mapToDto(Post post){
        PostsDto postsDto = new PostsDto();
        postsDto.setId(post.getId());
        postsDto.setTitle(post.getTitle());
        postsDto.setDescription(post.getDescription());
        postsDto.setContent(post.getContent());
        return postsDto;
    }


    private Post mapToEntity(PostsDto postsDto){
        Post post = new Post();
        post.setTitle(postsDto.getTitle());
        post.setDescription(postsDto.getDescription());
        post.setContent(postsDto.getContent());
        return post;
    }

    @Override
    public PostsDto getPostById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostsDto updatePost(PostsDto postsDto, Long id) {
        //get post by id from 
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postsDto.getTitle());
        post.setDescription(postsDto.getDescription());
        post.setContent(postsDto.getContent());

        Post updatedPost = postRepo.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepo.delete(post);
    }
    
    
}
