package me.minsungryu.restdocs.mapper;

import java.util.List;
import me.minsungryu.restdocs.domain.Post;
import me.minsungryu.restdocs.dto.PostDto;
import me.minsungryu.restdocs.dto.PostSimpleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

	PostDto map(Post post);

	@Mapping(target = "comments", ignore = true)
	Post map(PostSimpleDto postSimpleDto);

	PostSimpleDto mapSimple(Post post);

	List<PostDto> mapAsList(List<Post> posts);

	List<PostSimpleDto> mapAsSimpleList(List<Post> posts);

}
