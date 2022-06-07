package site.metacoding.blogproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import site.metacoding.blogproject.model.Reply;

public interface CommentRepository extends JpaRepository<Reply, Integer> {

}