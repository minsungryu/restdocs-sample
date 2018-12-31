package me.minsungryu.restdocs.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import me.minsungryu.restdocs.dto.PostDto;
import me.minsungryu.restdocs.dto.PostSimpleDto;
import me.minsungryu.restdocs.service.PostFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/posts")
public class PostFindController {

	private final PostFindService postFindService;

	@Autowired
	public PostFindController(PostFindService postFindService) {
		this.postFindService = postFindService;
	}

	@GetMapping
	public ResponseEntity<List<PostSimpleDto>> findAll() {
		return ResponseEntity.ok(postFindService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> findById(@PathVariable Long id) {
		return ResponseEntity.ok(postFindService.findById(id));
	}

}
