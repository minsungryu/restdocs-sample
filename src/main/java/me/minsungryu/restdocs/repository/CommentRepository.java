package me.minsungryu.restdocs.repository;

import me.minsungryu.restdocs.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
