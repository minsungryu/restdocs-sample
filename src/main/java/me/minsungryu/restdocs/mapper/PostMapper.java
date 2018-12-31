package me.minsungryu.restdocs.mapper;

import java.util.List;
import me.minsungryu.restdocs.domain.Post;
import me.minsungryu.restdocs.dto.PostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

	PostDto map(Post post);

	List<PostDto> mapAsList(List<Post> posts);

}
