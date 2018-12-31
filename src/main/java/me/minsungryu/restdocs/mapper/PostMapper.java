package me.minsungryu.restdocs.mapper;

import java.util.List;
import me.minsungryu.restdocs.domain.Post;
import me.minsungryu.restdocs.dto.PostDto;
import me.minsungryu.restdocs.dto.PostSimpleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

	PostDto map(Post post);

	PostSimpleDto simpleMap(Post post);

	List<PostDto> mapAsList(List<Post> posts);

	List<PostSimpleDto> simpleMapAsList(List<Post> posts);

}
