package site.metacoding.blogproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import site.metacoding.blogproject.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
