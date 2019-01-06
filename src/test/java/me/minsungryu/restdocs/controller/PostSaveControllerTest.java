package me.minsungryu.restdocs.controller;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import me.minsungryu.restdocs.common.RestControllerTest;
import me.minsungryu.restdocs.dto.PostSimpleDto;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

public class PostSaveControllerTest extends RestControllerTest {

	@Test
	public void save() throws Exception {
		PostSimpleDto newPost = new PostSimpleDto();
		newPost.setTitle("새로운 포스트 제목");
		newPost.setContent("새로운 포스트 내용");

		mockMvc.perform(post("/posts")
			.content(objectMapper.writeValueAsString(newPost))
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.accept(MediaType.APPLICATION_JSON_UTF8)
		)
			.andDo(print())
			.andDo(
				document("{class-name}/{method-name}",
					requestFields(
						fieldWithPath("id").type(JsonFieldType.NUMBER).description("포스트 Id").optional().ignored(),
						fieldWithPath("title").type(JsonFieldType.STRING).description("포스트 제목"),
						fieldWithPath("content").type(JsonFieldType.STRING).description("포스트 내용")
					),
					responseHeaders(
						headerWithName("Location").description("생성된 Post resource")
					)
				)
			)
			.andExpect(status().isCreated())
			.andExpect(header().exists("Location"))
		;
	}

}
