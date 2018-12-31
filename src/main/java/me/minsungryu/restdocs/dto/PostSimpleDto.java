package me.minsungryu.restdocs.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSimpleDto implements Serializable {

	private Long id;

	private String title;

	private String content;

}
