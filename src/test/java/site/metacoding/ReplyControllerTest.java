package site.metacoding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import site.metacoding.blogproject.model.Board;
import site.metacoding.blogproject.model.Reply;
import site.metacoding.blogproject.repository.BoardRepository;
import site.metacoding.blogproject.repository.ReplyRepository;

@RestController
public class ReplyControllerTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable int id) {
        return boardRepository.findById(id).get(); // jackson 라이브러리 (오브젝트를 json으로 리턴) => 모델의 getter를 호출
    }

    @GetMapping("/test/reply")
    public List<Reply> getReply() {
        return replyRepository.findAll(); // jackson 라이브러리 (오브젝트를 jspn으로 리턴)
    }
}
