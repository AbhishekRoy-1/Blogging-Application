package com.roy.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.roy.blog.payloads.PostDto;
import com.roy.blog.payloads.PostResponse;

@Service
public interface PostService {
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy);
	PostDto getPostById(Integer postId);
	List<PostDto> getPostsByCategory(Integer categoryId);
	List<PostDto> getPostsByUser(Integer userId);
	List<PostDto> searchPosts(String Keyword);
	

}
