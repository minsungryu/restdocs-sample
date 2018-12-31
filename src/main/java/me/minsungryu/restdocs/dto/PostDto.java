package me.minsungryu.restdocs.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto implements Serializable {

	private Long id;

	private String title;

	private String content;

	private List<CommentDto> comments = new ArrayList<>();

}
