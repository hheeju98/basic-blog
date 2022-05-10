package site.metacoding.blogproject.domain;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.metacoding.blogproject.Member;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)

// 인터넷 브라우저 요청은 무조건 get요청 밖에 할 수 없다.
// http://localhost:8080/http/get (select)
@RestController
public class BlogController {
    @GetMapping("/http/get")
    public String get(Member m) { // ?id=1&username=ssar&password=1234&email=ssar@nate.com member에 넣어줌
        return "get 요청" + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    // (insert)
    @PostMapping("/http/post") // text/plain, application/json
    public String post(@RequestBody Member m) { // MessageConverter (스프링부트) Json데이터 Member m에 매핑해서 넣어줌 텍스트 보내면 응답불가
                                                // 일반문자열은 매핑이 안됨
        return "post 요청" + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    // (update)
    @PutMapping("/http/put")
    public String put(@RequestBody Member m) {
        return "put 요청 :" + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    // (delete)
    @DeleteMapping("/http/delete")
    public String delete() {
        return "delete 요청";
    }
}
