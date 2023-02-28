package com.roy.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	private Integer postId;
	@NotEmpty
	private String title;
	@NotEmpty
	@Size(min = 10)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	private UserDto user;
	
	private Set<CommentDto> comments=new HashSet<>();


}
