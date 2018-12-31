package me.minsungryu.restdocs.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto implements Serializable {

	private String content;

}
