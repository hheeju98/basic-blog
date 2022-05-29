package site.metacoding.blogproject;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import site.metacoding.blogproject.model.RoleType;
import site.metacoding.blogproject.model.User;
import site.metacoding.blogproject.repository.UserRepository;

// html 파일이 아니라 data리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {
    @Autowired // 의존성 주입
    private UserRepository userRepository;
    // {id} 주소로 파라메터를 전달 받을 수 있음.
    // http://localhost:8000/blog/dummy/user/3
    // email, password

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);

        } catch (Exception e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다. ";
        }
        return "삭제되었습니다.id" + id;
    }

    @Transactional // 함수 종료시에 자동 commit이 됨.
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
        // json데이터를 요청 => Java Object(Message Converter의 Jackson라이브러리가 변환해서)로변환해서 받아준다.
        System.out.println("id : " + id);
        System.out.println("password : " + requestUser.getPassword());
        System.out.println("email : " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        // userRepository.save(user);

        // 더티 체킹
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한 페이지당 두건의 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public Page<User> pageList(
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // List<User> users = userRepository.findAll(pageable).getContent(); //
        // content안의 내용만 리턴
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return pagingUser;
    }

    // {id}
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // user/4를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 됨
        // 그럼 return null이 되는데 그럼 프로그램에 문제가 생김
        // Optional로 User객체를 감싸서 가져와서 null인지 아닌지 판단해서 return

        // 람다식
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당사용자는 없습니다.");
        });

        // 요청 : 웹브라우저 /자바스크립트나 html만 이해가능
        // user 객체 = 자바 오브젝트
        // 변환 (웹브라우저가 이해 할 수 있는 데이터로) = json(Gson라이브러리)
        // 스프링부트 = Message Converter가 응답시에 자동 작동
        // 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져줌
        return user;
    }

    // http://localhost:8000/blog/dummy/join (요청)
    // http의 body에 username, password, email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user) // parameter // key=value (약속된 규칙) / 오브젝트로도 받을 수 있음
    {
        System.out.println("id : " + user.getId());
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("role : " + user.getRole());
        System.out.println("createDate : " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
