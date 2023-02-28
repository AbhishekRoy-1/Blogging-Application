package com.roy.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.roy.blog.payloads.CommentDto;
import com.roy.blog.payloads.CommentResponse;

@Service
public interface CommentService {
	CommentDto createComment(CommentDto commentDto, Integer userId,Integer postId);
	CommentDto updateComment(CommentDto commentDto,Integer commentId);
	CommentDto getCommentById(Integer commentId);
	List<CommentDto> getCommentByUserId(Integer userId);
	List<CommentDto> getCommentByPostId(Integer postId);
	CommentResponse getAllComments(Integer pageNumber,Integer pageSize,String sortBy);
	void deleteComment(Integer commentId);

	
	

}
