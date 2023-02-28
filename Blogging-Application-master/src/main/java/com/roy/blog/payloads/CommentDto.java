package com.roy.blog.payloads;

import java.util.Date;

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
public class CommentDto {
	private Integer commentId;
	
	@NotEmpty
	@Size(min = 10)
	private String comment;
	private Date addedDate;
	
	



}
