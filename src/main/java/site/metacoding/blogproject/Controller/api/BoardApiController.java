package site.metacoding.blogproject.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import site.metacoding.blogproject.config.auth.PrincipalDetail;
import site.metacoding.blogproject.dto.ReplySaveRequestDto;
import site.metacoding.blogproject.dto.ResponseDto;
import site.metacoding.blogproject.model.Board;
import site.metacoding.blogproject.model.Reply;
import site.metacoding.blogproject.model.RoleType;
import site.metacoding.blogproject.model.User;
import site.metacoding.blogproject.service.BoardService;
import site.metacoding.blogproject.service.UserService;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        System.out.println("BoardApiController : update : id : " + id);
        System.out.println("BoardApiController : update : board : " + board.getTitle());
        System.out.println("BoardApiController : update : board : " + board.getContent());
        boardService.글수정하기(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    // 데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
    // dto를 사용하지 않은 이유는
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
        boardService.댓글쓰기(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.댓글삭제(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    // 스프링 시큐리티 이용해서 로그인!
    /*
     * @PostMapping("/api/user/login")
     * public ResponseDto<Integer> login(@RequestBody User user, HttpSession
     * session) {
     * System.out.println("UserApiController : login호출됨");
     * User principal = userService.로그인(user); // principal (접근 주체라는 뜻)
     * 
     * if (principal != null) {
     * session.setAttribute("principal", principal);
     * }
     * return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
     * }
     */
}
