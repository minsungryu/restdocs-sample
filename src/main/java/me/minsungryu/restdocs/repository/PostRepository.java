package me.minsungryu.restdocs.repository;

import me.minsungryu.restdocs.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
