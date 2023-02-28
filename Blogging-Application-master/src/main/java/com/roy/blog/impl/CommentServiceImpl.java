package com.roy.blog.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.roy.blog.entities.Comment;
import com.roy.blog.entities.Post;
import com.roy.blog.entities.User;
import com.roy.blog.exceptions.ResourceNotFoundException;
import com.roy.blog.payloads.CommentDto;
import com.roy.blog.payloads.CommentResponse;
import com.roy.blog.repositories.CommentRepo;
import com.roy.blog.repositories.PostRepo;
import com.roy.blog.repositories.UserRepo;
import com.roy.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto,Integer userId, Integer postId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		Post post= this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post","Post Id", postId));						
		Comment comment=this.modelMapper.map(commentDto,Comment.class);
		comment.setAddedDate(new Date());	
		comment.setUser(user);
		comment.setPost(post);
		Comment newComment=this.commentRepo.save(comment);
		return this.modelMapper.map(newComment, CommentDto.class);
						
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", commentId));
		comment.setComment(commentDto.getComment());
		Comment newComment=this.commentRepo.save(comment);
		return this.modelMapper.map(newComment, CommentDto.class);
	}

	@Override
	public CommentDto getCommentById(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", commentId));
		return this.modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public List<CommentDto> getCommentByUserId(Integer userId) {
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		List<Comment> comments =this.commentRepo.findByUser(user);
		List<CommentDto> commentDtos=comments.stream().map((comment)->this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		return commentDtos;
	}


	@Override
	public List<CommentDto> getCommentByPostId(Integer postId) {
		Post post=this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		List<Comment> comments =this.commentRepo.findByPost(post);
		List<CommentDto> commentDtos=comments.stream().map((comment)->this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		return commentDtos;
	}

	@Override
	public CommentResponse getAllComments(Integer pageNumber, Integer pageSize,String sortBy) {
		Pageable p=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).ascending());
		Page<Comment> pageComment= this.commentRepo.findAll(p);
		List<Comment> comments=pageComment.getContent();
		List<CommentDto> commentDtos=comments.stream().map((comment)->this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
		CommentResponse commentResponse=new CommentResponse();
		commentResponse.setComment(commentDtos);
		commentResponse.setPageNumber(pageComment.getNumber());
		commentResponse.setPageSize(pageComment.getSize());
		commentResponse.setTotalElements(pageComment.getTotalElements());
		commentResponse.setTotalPages(pageComment.getTotalPages());
		commentResponse.setLastPage(pageComment.isLast());
		return commentResponse;		
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", commentId));
		this.commentRepo.delete(comment);
		
	}


	

}
