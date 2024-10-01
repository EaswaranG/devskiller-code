package com.devskiller.tasks.blog.model;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 4096)
	private String comment;

	@Column(length = 100)
	private String author;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime creationDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "post_id")
	private Post post;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public CommentDto toRecord(){
		return new CommentDto(id, comment, author, creationDate);
	}


	public Comment() {
	}

	public Comment(String author, String comment, Post post) {
		this.author = author;
		this.comment = comment;
		this.post = post;
	}
}
