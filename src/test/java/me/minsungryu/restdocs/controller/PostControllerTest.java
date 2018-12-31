package me.minsungryu.restdocs.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.stream.Collectors;
import me.minsungryu.restdocs.configuration.RestDocsConfiguration;
import me.minsungryu.restdocs.dto.CommentDto;
import me.minsungryu.restdocs.dto.PostDto;
import me.minsungryu.restdocs.service.PostFindService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureRestDocs
@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
@Import(RestDocsConfiguration.class)
public class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PostFindService postFindService;

	@Test
	public void findAll() throws Exception {
		//given
		PostDto postDto1 = createPostDto("Post title1", "Post content1", "some comment");
		PostDto postDto2 = createPostDto("Post title2", "Post content2", "other comment", "another comment");
		given(postFindService.findAll()).willReturn(Arrays.asList(postDto1, postDto2));

		//when
		ResultActions result = mockMvc.perform(get("/posts")
			.accept(MediaType.APPLICATION_JSON)
		);

		//then
		result.andExpect(status().isOk())
			.andDo(
				document("{class-name}/{method-name}",
					responseFields(
						fieldWithPath("[].title").type(JsonFieldType.STRING).description("포스트 제목"),
						fieldWithPath("[].content").type(JsonFieldType.STRING).description("포스트 내용"),
						fieldWithPath("[].comments").type(JsonFieldType.ARRAY).description("댓글"),
						fieldWithPath("[].comments[].content").type(JsonFieldType.STRING).description("댓글 내용")
					)
				)
			);
	}

	private PostDto createPostDto(String title, String content, String... comments) {
		PostDto postDto = new PostDto();
		postDto.setTitle(title);
		postDto.setContent(content);
		postDto.setComments(Arrays.stream(comments)
			.map(comment -> {
				CommentDto commentDto = new CommentDto();
				commentDto.setContent(comment);
				return commentDto;
			}).collect(Collectors.toList()));
		return postDto;
	}

}
