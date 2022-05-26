package site.metacoding.blogproject.Controller.api;

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

import site.metacoding.blogproject.Controller.dto.ResponseDto;
import site.metacoding.blogproject.auth.PrincipalDetail;
import site.metacoding.blogproject.domain.Board;
import site.metacoding.blogproject.domain.RoleType;
import site.metacoding.blogproject.domain.User;
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
        boardService.글수정하기(id, board);
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