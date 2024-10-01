package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.service.CommentService;
import org.hibernate.annotations.Comments;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.service.PostService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;
	private final CommentService commentService;

	public PostController(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PostDto getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}


	@PostMapping(value = "/{id}/comments", consumes = "Application/Json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Long> postComments(@PathVariable Long id, @RequestBody NewCommentDto newComment) {
		Long commentID = commentService.addComment(id, newComment);
		if (commentID == 0L) {
			return new ResponseEntity<>(commentID, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(id, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}/comments")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long id) {
		List<CommentDto> commentsList = commentService.getCommentsForPost(id);
		if(commentsList.isEmpty()){
			return new ResponseEntity<>(commentsList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(commentsList, HttpStatus.OK);
	}

}
