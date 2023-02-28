package com.roy.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.roy.blog.entities.Comment;
import com.roy.blog.entities.Post;
import com.roy.blog.entities.User;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
	
	List<Comment> findByUser(User user);
	
	List<Comment> findByPost(Post post);
}
