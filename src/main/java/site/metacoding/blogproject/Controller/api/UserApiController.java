package site.metacoding.blogproject.Controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import site.metacoding.blogproject.config.auth.PrincipalDetail;
import site.metacoding.blogproject.domain.RoleType;
import site.metacoding.blogproject.domain.User;
import site.metacoding.blogproject.dto.ResponseDto;
import site.metacoding.blogproject.service.UserService;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/s/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email (user가 들고 있다.)
        System.out.println("UserApiController : save 호출됨");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) { // key=value,
        // x-www-form-urlencoded
        // (requestBody가
        // 없을
        // 경우, json데이터 받지 않을 경우)
        userService.회원수정(user);
        // 여기서는 트랜잭션이 종료되기 때문에 DB값은 변경이 됬음.
        // 하지만 세션값은 변경되지 않은 상태이기 때문에 직접 세션값을 변경해 줘야 한다.

        // 세션 등록
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

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
