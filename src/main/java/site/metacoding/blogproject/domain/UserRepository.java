package site.metacoding.blogproject.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// DAO
// 자동으로 bean등록이 된다,
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

    // SELECT * FROM user WHERE username = 1?;
    Optional<User> findByUsername(String username);
    // JPA Naming 쿼리 전략
    // SELECT * FROM user WHERE username = ?1 AND password = ?2;
    // User findByUsernameAndPassword(String username, String password);

    // @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2;",
    // nativeQuery = true)
    // User login(String username, String password);
}
// 해당 레파지토리는 유저테이블을 관리, 프라이머리 키는 integer이다.
// jpa는 findall();을 들고 있게 된다. 모든 행 리턴
