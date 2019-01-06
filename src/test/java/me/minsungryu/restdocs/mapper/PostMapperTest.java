package me.minsungryu.restdocs.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.minsungryu.restdocs.domain.Comment;
import me.minsungryu.restdocs.domain.Post;
import me.minsungryu.restdocs.dto.PostDto;
import me.minsungryu.restdocs.dto.PostSimpleDto;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

public class PostMapperTest {

	private static List<Post> posts = new ArrayList<>();

	private PostMapper postMapper = Mappers.getMapper(PostMapper.class);

	@Before
	public void setUp() {
		Post post1 = new Post();
		post1.setId(1L);
		post1.setTitle("title1");
		post1.setContent("content1");

		Comment comment1Post1 = new Comment();
		comment1Post1.setId(1L);
		comment1Post1.setContent("comment1");
		comment1Post1.setPost(post1);

		Comment comment2Post1 = new Comment();
		comment2Post1.setId(2L);
		comment2Post1.setContent("comment2");
		comment2Post1.setPost(post1);

		post1.setComments(Arrays.asList(comment1Post1, comment2Post1));
		posts.add(post1);

		Post post2 = new Post();
		post1.setId(2L);
		post1.setTitle("title2");
		post1.setContent("content2");

		Comment comment1Post2 = new Comment();
		comment1Post2.setId(3L);
		comment1Post2.setContent("comment3");
		comment1Post2.setPost(post2);

		Comment comment2Post2 = new Comment();
		comment2Post2.setId(4L);
		comment2Post2.setContent("comment4");
		comment2Post2.setPost(post2);

		post2.setComments(Arrays.asList(comment1Post2, comment2Post2));

		posts.add(post2);
	}

	@Test
	public void map() {
		Post post = posts.get(0);
		PostDto postDto = postMapper.map(post);

		assertThat(postDto).isNotNull();
		assertThat(postDto.getTitle()).isNotNull();
		assertThat(postDto.getContent()).isNotNull();
		assertThat(postDto.getComments()).isNotEmpty();
	}

	@Test
	public void mapSimple() {
		Post post = posts.get(0);
		PostSimpleDto postSimpleDto = postMapper.mapSimple(post);

		assertThat(postSimpleDto).isNotNull();
		assertThat(postSimpleDto.getTitle()).isNotNull();
		assertThat(postSimpleDto.getContent()).isNotNull();
	}

	@Test
	public void mapSimpleToEntity() {
		PostSimpleDto postSimpleDto = new PostSimpleDto();
		postSimpleDto.setId(1L);
		postSimpleDto.setTitle("title");
		postSimpleDto.setContent("content");

		Post post = postMapper.map(postSimpleDto);

		assertThat(post).isNotNull();
		assertThat(post.getTitle()).isNotNull();
		assertThat(post.getContent()).isNotNull();
	}

	@Test
	public void mapAsList() {
		List<PostDto> postDtos = postMapper.mapAsList(posts);

		assertThat(postDtos).isNotEmpty();
		assertThat(postDtos).hasSize(posts.size());
		assertThat(postDtos)
			.extracting(PostDto::getId, PostDto::getTitle, PostDto::getContent, PostDto::getComments)
			.isNotEmpty();
	}

	@Test
	public void mapAsSimpleList() {
		List<PostSimpleDto> postSimpleDtos = postMapper.mapAsSimpleList(posts);

		assertThat(postSimpleDtos).isNotEmpty();
		assertThat(postSimpleDtos).hasSize(posts.size());
		assertThat(postSimpleDtos)
			.extracting(PostSimpleDto::getId, PostSimpleDto::getTitle, PostSimpleDto::getContent)
			.isNotEmpty();
	}

}
