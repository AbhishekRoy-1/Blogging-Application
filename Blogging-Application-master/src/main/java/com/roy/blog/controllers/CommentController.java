package com.roy.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roy.blog.config.AppConstant;
import com.roy.blog.payloads.ApiResponse;
import com.roy.blog.payloads.CommentDto;
import com.roy.blog.payloads.CommentResponse;
import com.roy.blog.services.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	//Create a Comment
	@PostMapping("/user/{userId}/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable Integer userId, @PathVariable Integer postId ){
		CommentDto createComment=this.commentService.createComment(commentDto, userId, postId);
		
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}
	
	//get comment by UserId
	@GetMapping("user/{userId}/comments")
	public ResponseEntity<List<CommentDto>> getCommentByUser(@PathVariable Integer userId){
		List<CommentDto> commentDtos=this.commentService.getCommentByUserId(userId);
		return new ResponseEntity<List<CommentDto>>(commentDtos,HttpStatus.OK);		
	}
	
	//get comment by PostId
	@GetMapping("post/{postId}/comments")
	public ResponseEntity<List<CommentDto>> getCommentByPost(@PathVariable Integer postId){
		List<CommentDto> commentDtos=this.commentService.getCommentByPostId(postId);
		return new ResponseEntity<List<CommentDto>>(commentDtos,HttpStatus.OK);		
	}
	
	//get comment by comment Id
	@GetMapping("comments/{commentId}")
	 public ResponseEntity<CommentDto> getPostByPostId(@PathVariable Integer commentId){
			CommentDto commentDto= this.commentService.getCommentById(commentId);
			return new ResponseEntity<CommentDto>(commentDto,HttpStatus.OK);
		 }

	//get all comments
	@GetMapping("/comments")
	 public ResponseEntity<CommentResponse> getAllComments(
			 @RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			 @RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize,
			 @RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false) String SortBy){
		 CommentResponse commentResponse=this.commentService.getAllComments(pageNumber,pageSize,SortBy); 
		 return new ResponseEntity<CommentResponse>(commentResponse,HttpStatus.OK);
	 }
	
	//update a comment
	 @PutMapping("/comments/{commentId}")
	 public ResponseEntity<CommentDto> updatePost(@RequestBody CommentDto commentDto,@PathVariable Integer commentId){
		 CommentDto updateComment=this.commentService.updateComment(commentDto, commentId);
		 return new ResponseEntity<CommentDto>(updateComment,HttpStatus.OK);
		 
	 }
	
	//delete a comment
	 @DeleteMapping("/comments/{commentId}")
	 public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer commentId) {
		 this.commentService.deleteComment(commentId);
		 return new ResponseEntity<ApiResponse>(new ApiResponse( "Comment is Successfully Deleted", true),HttpStatus.OK);
		 
	 }
	 
	
	
 
	
}
