package me.minsungryu.restdocs.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.minsungryu.restdocs.dto.PostSimpleDto;
import me.minsungryu.restdocs.service.PostSaveService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostSaveController {

	private final PostSaveService postSaveService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> save(@RequestBody PostSimpleDto postDto) {
		PostSimpleDto newPostDto = postSaveService.save(postDto);
		return ResponseEntity.created(URI.create("/posts/" + newPostDto.getId())).build();
	}

}
