package site.metacoding.blogproject.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터 쓸때 사용
    private String content; // 섬머노트라는 라이브러리 <htm>태그가 섞여서 디자인이 됨.

    private int count; // 조회수

    @ManyToOne(fetch = FetchType.EAGER) // user, userId의 연관관계 만들어줌 / Many = Board, User = One 한명의 유저가 여러개의 게시글
    @JoinColumn(name = "userId") // 디비에 만드렁질때 userId라는 이름으로 만들어줄것임
    private User user; // 디비는 오브젝트를 저장할 수 없다 그래서 fk 사용 / 자바는 오브젝트를 저장할 숭 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 필요하면 들고오고 필요없으면 안들고 디폴트는 eager // 하나의 게시글은 여러개의 답변을 가질
                                                            // 수있다. // mappedBy 연관관계의 주인이 아니다 (FK가 아니다) DB에 칼럼 만들지마. 주인이
    // FK가 reply테이블의 board가 주인이라는 뜻. 디비에 들어가 있는 값이 아니다.

    // @JoinColumn(name = "replyId" ) // 필요없음 보드 테이블에 replyId라는 외래키가 필요 없다
    private List<Reply> reply; // 한건이 아니므로

    @CreationTimestamp // 데이터가 인서트나 업데이트될때 자동으로 값이 들어감
    private Timestamp createDate;

}
