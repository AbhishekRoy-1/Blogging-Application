package com.roy.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roy.blog.entities.Category;
import com.roy.blog.entities.Post;
import com.roy.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
		
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
	
	//Above three methods are custom finder methods made specially to find the posts by User and Categories

}
