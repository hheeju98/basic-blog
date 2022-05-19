package site.metacoding.blogproject.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Java (다른언어) Object - > 테이블로 매핑해주는 기술
@Entity // 클래스를 테이블화 User클래스가 MySQL에 테이블이 생성이 된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!!
// @DynamicInsert / Insert시에 null인 필드를 제외시켜준다.
public class User {
    @Id // 프라이머리키라는것을 알려줌
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 db의 넘버링 전략을 따라간다

    private int id; // primary key시퀀스, auto_increment

    @Column(nullable = false, length = 30, unique = true)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 비밀번호 해쉬로 변경해서 암호화할 것이기 때문에 넉넉하게 100으로
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // @ColumnDefault(" 'user' ") // 회원가입할때 디폴트값 user로 줌 / 디비에서 varchar문자열로 쓸꺼라서
    // 홑따옴표 붙임
    // DB는 RoleType이라는게 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰는게 좋다. ADMIN,USER 타입 강제// admin, user, manager 등의 권한 관리

    @CreationTimestamp
    private Timestamp createDate;
}
