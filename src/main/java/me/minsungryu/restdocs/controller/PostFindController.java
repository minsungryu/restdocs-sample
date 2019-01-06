package me.minsungryu.restdocs.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.minsungryu.restdocs.dto.PostDto;
import me.minsungryu.restdocs.dto.PostSimpleDto;
import me.minsungryu.restdocs.service.PostFindService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostFindController {

	private final PostFindService postFindService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<PostSimpleDto>> findAll() {
		return ResponseEntity.ok(postFindService.findAll());
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PostDto> findById(@PathVariable Long id) {
		return ResponseEntity.ok(postFindService.findById(id));
	}

}
