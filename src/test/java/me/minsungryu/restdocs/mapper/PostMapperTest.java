package me.minsungryu.restdocs.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import me.minsungryu.restdocs.domain.Post;
import me.minsungryu.restdocs.dto.PostDto;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

public class PostMapperTest {

	PostMapper postMapper = Mappers.getMapper(PostMapper.class);

	@Test
	public void map() {
		Post post = createPost("title", "content");

		PostDto postDto = postMapper.map(post);

		assertThat(postDto).isNotNull();
		assertThat(postDto.getTitle()).isEqualTo("title");
		assertThat(postDto.getContent()).isEqualTo("content");
		assertThat(postDto.getComments()).isEmpty();
	}

	@Test
	public void mapAsList() {
		Post post1 = createPost("title1", "content1");
		Post post2 = createPost("title2", "content2");

		List<PostDto> postDtos = postMapper.mapAsList(Arrays.asList(post1, post2));

		assertThat(postDtos).isNotEmpty();
		assertThat(postDtos).hasSize(2);
	}

	private Post createPost(String title, String content) {
		Post post = new Post();
		post.setTitle(title);
		post.setContent(content);
		return post;
	}

}
