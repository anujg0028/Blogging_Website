package com.anuj.blog.services;

import java.util.List;

import com.anuj.blog.payloads.PostDto;
import com.anuj.blog.payloads.PostResponse;

public interface PostService {

	// create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// Update
	PostDto updatePost(PostDto postDto, Integer postId);

	// delete
	void deletePost(Integer postId);

	// get all posts
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortOder);

	// get single post
	PostDto getPostById(Integer postId);

	// get all posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);

	// get all posts by user
	List<PostDto> getPostsByUser(Integer userId);

	// search posts
	List<PostDto> searchPosts(String keyword);

}
