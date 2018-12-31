package me.minsungryu.restdocs.service;

import java.util.List;
import me.minsungryu.restdocs.dto.PostDto;
import me.minsungryu.restdocs.mapper.PostMapper;
import me.minsungryu.restdocs.repository.CommentRepository;
import me.minsungryu.restdocs.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostFindService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostMapper postMapper;

	public List<PostDto> findAll() {
		return postMapper.mapAsList(postRepository.findAll());
	}

}
