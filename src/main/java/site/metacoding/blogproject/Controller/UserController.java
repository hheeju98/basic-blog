package site.metacoding.blogproject.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code) { // ResponseBody 붙이면 Data를 리턴해주는 컨트롤러 함수
        // POST방식으로 key=value 데이터를 요청 (카카오 쪽으로)
        // Retrofit2
        // OkHttp
        // RestTemplate

        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // ContentType을 담는 것은 지금 전송할 바디 데이터가 key&value형태임을 알려준다.

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "f472a309b4a99f56ce0886338e1f94fb");
        params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
        params.add("code", code); // 동적으로 받는다!

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest, // 바디에 들어갈 데이터와 헤더 값
                String.class);

        return "카카오 토큰 요청 완료 : 토큰 요청에 대한 응답: " + response;
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }
}
