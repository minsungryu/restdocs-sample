package me.minsungryu.restdocs.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class PostFindControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void findAll() throws Exception {
		mockMvc.perform(get("/posts").accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andDo(
				document("{class-name}/{method-name}",
					responseFields(
						fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("포스트 id"),
						fieldWithPath("[].title").type(JsonFieldType.STRING).description("포스트 제목"),
						fieldWithPath("[].content").type(JsonFieldType.STRING).description("포스트 내용")
					)
				)
			)
			.andExpect(status().isOk());
	}

	@Test
	public void findById() throws Exception {
		Long postId = 1L;
		mockMvc.perform(get("/posts/{id}", postId).accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andDo(
				document("{class-name}/{method-name}",
					pathParameters(
						parameterWithName("id").description("포스트 id")
					),
					responseFields(
						fieldWithPath("id").type(JsonFieldType.NUMBER).description("포스트 id"),
						fieldWithPath("title").type(JsonFieldType.STRING).description("포스트 제목"),
						fieldWithPath("content").type(JsonFieldType.STRING).description("포스트 내용"),
						fieldWithPath("comments").type(JsonFieldType.ARRAY).description("댓글"),
						fieldWithPath("comments[].id").type(JsonFieldType.NUMBER).description("댓글 id"),
						fieldWithPath("comments[].content").type(JsonFieldType.STRING).description("댓글 내용")
					)
				)
			)
			.andExpect(status().isOk());
	}

}
