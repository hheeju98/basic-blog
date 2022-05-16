package site.metacoding.blogproject.Controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import site.metacoding.blogproject.Controller.dto.ResponseDto;
import site.metacoding.blogproject.domain.RoleType;
import site.metacoding.blogproject.domain.User;
import site.metacoding.blogproject.service.UserService;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/s/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email (user가 들고 있다.)
        System.out.println("UserApiController : save 호출됨");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
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
