package me.minsungryu.restdocs.service;

import lombok.RequiredArgsConstructor;
import me.minsungryu.restdocs.domain.Post;
import me.minsungryu.restdocs.dto.PostSimpleDto;
import me.minsungryu.restdocs.mapper.PostMapper;
import me.minsungryu.restdocs.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSaveService {

	private final PostRepository postRepository;

	private final PostMapper postMapper;

	public PostSimpleDto save(PostSimpleDto postDto) {
		Post post = postMapper.map(postDto);
		Post newPost = postRepository.save(post);
		return postMapper.mapSimple(newPost);
	}

}
