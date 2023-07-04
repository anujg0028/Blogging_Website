package com.anuj.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer postId;
	private String content;
	private String imageName;
	private String title;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments = new HashSet<>(); // we put this here since we didn't wanted to make get api for
														// Comment, this is for seeing the comments via post's get api

	// here we saw infinite loop(when using Category and User instead to CategoryDto
	// and UserDto) while creating a post as postDto had user and
	// category which in turn had post, so we replaced category and user with
	// categoryDto and UserDto. Now CategoryDto and UserDto doesn't has post so
	// infinitely running is no more a problem

}
