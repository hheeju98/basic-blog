package site.metacoding.blogproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /s/** 이하만 허용
// 그냥 주소가 / 이면 index.mustache 허용
// static이하에 있는 /js/**, /css/**, /image/** 허용 */ */ */
@Controller
public class UserController {

    // 회원가입 하러 들어가는데 인증이 되어 있을 필요는 없다.
    @GetMapping("/s/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/s/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }
}
