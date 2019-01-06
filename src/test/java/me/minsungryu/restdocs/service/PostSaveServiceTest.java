package me.minsungryu.restdocs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import me.minsungryu.restdocs.domain.Post;
import me.minsungryu.restdocs.dto.PostSimpleDto;
import me.minsungryu.restdocs.mapper.PostMapper;
import me.minsungryu.restdocs.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PostSaveServiceTest {

	private static final Long POST_ID = 1L;

	private static final String POST_TITLE = "title";

	private static final String POST_CONTENT = "content";

	@InjectMocks
	private PostSaveService postSaveService;

	@Mock
	private PostRepository postRepository;

	@Mock
	private PostMapper postMapper;

	@Test
	public void save() {
		PostSimpleDto postSimpleDto = new PostSimpleDto();
		postSimpleDto.setTitle(POST_TITLE);
		postSimpleDto.setContent(POST_CONTENT);

		Post postBeforeSave = new Post();
		postBeforeSave.setTitle(POST_TITLE);
		postBeforeSave.setContent(POST_CONTENT);

		Post postAfterSave = new Post();
		postAfterSave.setId(POST_ID);
		postAfterSave.setTitle(POST_TITLE);
		postAfterSave.setContent(POST_CONTENT);

		PostSimpleDto savedPostSimpleDto = new PostSimpleDto();
		savedPostSimpleDto.setId(POST_ID);
		savedPostSimpleDto.setTitle(POST_TITLE);
		savedPostSimpleDto.setContent(POST_CONTENT);

		// given
		given(postMapper.map(postSimpleDto)).willReturn(postBeforeSave);
		given(postRepository.save(postBeforeSave)).willReturn(postAfterSave);
		given(postMapper.mapSimple(postAfterSave)).willReturn(savedPostSimpleDto);

		// when
		PostSimpleDto result = postSaveService.save(postSimpleDto);

		// then
		assertThat(result.getId()).isNotNull();
		assertThat(result.getTitle()).isEqualTo(postSimpleDto.getTitle());
		assertThat(result.getContent()).isEqualTo(postSimpleDto.getContent());
	}

}
