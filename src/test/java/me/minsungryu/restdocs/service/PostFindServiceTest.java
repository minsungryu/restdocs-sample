package me.minsungryu.restdocs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import me.minsungryu.restdocs.domain.Post;
import me.minsungryu.restdocs.dto.PostDto;
import me.minsungryu.restdocs.dto.PostSimpleDto;
import me.minsungryu.restdocs.mapper.PostMapper;
import me.minsungryu.restdocs.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PostFindServiceTest {

	@InjectMocks
	private PostFindService postFindService;

	@Mock
	private PostRepository postRepository;

	@Mock
	private PostMapper postMapper;

	@Test
	public void findAll() {
		// given
		List<Post> posts = Arrays.asList(new Post(), new Post());
		List<PostSimpleDto> postSimpleDtos = Arrays.asList(new PostSimpleDto(), new PostSimpleDto());
		given(postRepository.findAll()).willReturn(posts);
		given(postMapper.simpleMapAsList(posts)).willReturn(postSimpleDtos);

		// when
		List<PostSimpleDto> result = postFindService.findAll();

		// then
		assertThat(result).isNotEmpty();
	}

	@Test
	public void findById() {
		Long postId = 1L;

		Post post = new Post();
		post.setId(postId);

		PostDto postDto = new PostDto();
		postDto.setId(postId);

		// given
		given(postRepository.findById(postId)).willReturn(Optional.of(post));
		given(postMapper.map(post)).willReturn(postDto);

		// when
		PostDto result = postFindService.findById(postId);

		// then
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(postId);
	}

	@Test(expected = EntityNotFoundException.class)
	public void findById_존재하지_않는_Id() {
		Long strangeId = 999L;
		// given
		given(postRepository.findById(strangeId)).willReturn(Optional.empty());

		// when & then throw
		postFindService.findById(strangeId);
	}
}
