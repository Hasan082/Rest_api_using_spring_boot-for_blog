package com.blog.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapp.model.Post;

public interface postRepository extends JpaRepository<Post, Long> {

}
