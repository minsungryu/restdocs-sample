package me.minsungryu.restdocs.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto implements Serializable {

	private String title;

	private String content;

	private List<CommentDto> comments;

}
