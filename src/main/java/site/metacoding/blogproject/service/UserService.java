package site.metacoding.blogproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogproject.domain.User;
import site.metacoding.blogproject.domain.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true) // select할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지 가능해짐 )
    public User 로그인(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
