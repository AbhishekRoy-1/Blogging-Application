package com.roy.blog.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "comment_date", nullable = false)
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	@ManyToOne
	private User user;
	
	
	
	
	

}
