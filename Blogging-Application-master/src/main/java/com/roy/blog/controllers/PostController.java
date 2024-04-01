package com.roy.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.roy.blog.config.AppConstants;
import com.roy.blog.payloads.ApiResponse;
import com.roy.blog.payloads.PostDto;
import com.roy.blog.payloads.PostResponse;
import com.roy.blog.services.FileService;
import com.roy.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	
	@Value("$ {project.image}")
	private String path;
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto createdpost=this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createdpost,HttpStatus.CREATED);		
	}
	
	//get Post by User
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> postDtos=this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	//get Post by Category
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> postDtos=this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	//GetAll Posts
	 @GetMapping("/posts")
	 public ResponseEntity<PostResponse> getAllPosts(
			 @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			 @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			 @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String SortBy){
		 PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,SortBy); 
		 return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	 }
	 
	 //Get Post by PostId
	 @GetMapping("/posts/{postId}")
	 public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId){
		PostDto postDto= this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		
	 }
	 
	 //To Delete Post
	 @DeleteMapping("/posts/{postId}")
	 public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		 this.postService.deletePost(postId);
		 return new ResponseEntity<ApiResponse>(new ApiResponse( "Post is Successfully Deleted", true),HttpStatus.OK);
		 
	 }
	 
	 //To update Post
	 @PutMapping("/posts/{postId}")
	 public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
		 PostDto updatePost=this.postService.updatePost(postDto, postId);
		 return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		 
	 }
	 
	 //Search
	 @GetMapping("/posts/search/{keywords}")
	 public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords){
		 List<PostDto> result=this.postService.searchPosts(keywords);
		 return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
		 
	 }
	 //post image upload
	 @PostMapping("/posts/image/upload/{postId}")
	 public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException{
	    PostDto postDto=this.postService.getPostById(postId); 
		String fileName=this.fileService.uploadImage(path, image);	
		postDto.setImageName(fileName);
		PostDto updatedPost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	 }
	 
	 //method to serve file
	 @GetMapping(value = "/posts/image/{imageName}",produces =MediaType.IMAGE_PNG_VALUE )
	 public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		 InputStream resourceInputStream=this.fileService.getResource(path, imageName);
		 response.setContentType(MediaType.IMAGE_PNG_VALUE);
		 StreamUtils.copy(resourceInputStream,response.getOutputStream());
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
