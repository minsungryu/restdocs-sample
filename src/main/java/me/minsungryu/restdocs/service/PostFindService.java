package me.minsungryu.restdocs.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import me.minsungryu.restdocs.dto.PostDto;
import me.minsungryu.restdocs.dto.PostSimpleDto;
import me.minsungryu.restdocs.mapper.PostMapper;
import me.minsungryu.restdocs.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostFindService {

	private final PostRepository postRepository;

	private final PostMapper postMapper;

	@Autowired
	public PostFindService(PostRepository postRepository, PostMapper postMapper) {
		this.postRepository = postRepository;
		this.postMapper = postMapper;
	}

	public List<PostSimpleDto> findAll() {
		return postMapper.simpleMapAsList(postRepository.findAll());
	}

	public PostDto findById(Long id) {
		return postRepository.findById(id)
			.map(postMapper::map)
			.orElseThrow(EntityNotFoundException::new);
	}

}
