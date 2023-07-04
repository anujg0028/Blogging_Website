package com.anuj.blog.repositories;

import java.util.List;

import com.anuj.blog.entities.Category;
import com.anuj.blog.entities.Post;
import com.anuj.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String title);
}
