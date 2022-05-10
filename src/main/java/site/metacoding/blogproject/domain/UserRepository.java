package site.metacoding.blogproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// 자동으로 bean등록이 된다,
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

}
// 해당 레파지토리는 유저테이블을 관리, 프라이머리 키는 integer이다.
// jpa는 findall();을 들고 있게 된다. 모든 행 리턴
