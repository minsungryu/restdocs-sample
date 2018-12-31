package me.minsungryu.restdocs.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import me.minsungryu.restdocs.dto.PostDto;
import me.minsungryu.restdocs.service.PostFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostFindService postFindService;

	@Autowired
	public PostController(PostFindService postFindService) {
		this.postFindService = postFindService;
	}

	@GetMapping
	public List<PostDto> findAll() {
		return postFindService.findAll();
	}

}
