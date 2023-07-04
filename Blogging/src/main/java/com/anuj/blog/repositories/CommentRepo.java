package com.anuj.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anuj.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
