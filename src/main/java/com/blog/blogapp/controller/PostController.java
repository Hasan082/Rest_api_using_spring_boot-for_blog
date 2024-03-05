package com.blog.blogapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapp.payload.PostsDto;
import com.blog.blogapp.services.PostService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostsDto> createPost(@RequestBody PostsDto postsDto){
        return new ResponseEntity<>(postService.createPost(postsDto),HttpStatus.CREATED);
    }

    //Get all post API
    @GetMapping
    public List<PostsDto> getAllPosts(){
        return postService.getAllPosts();
    }

    //Get post by ID
    @GetMapping("/{id}")
    public ResponseEntity<PostsDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    };


    //Update post API
    @PutMapping("/{id}")
    public ResponseEntity<PostsDto> updatePost(@RequestBody PostsDto postsDto,@PathVariable(name = "id") long id){
       PostsDto postResponse = postService.updatePost(postsDto, id);
        
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //Delete post by Id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<String>("Post is deleted Successfully", HttpStatus.OK);
    }


}
