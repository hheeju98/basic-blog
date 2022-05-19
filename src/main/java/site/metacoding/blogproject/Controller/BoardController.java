package site.metacoding.blogproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import site.metacoding.blogproject.auth.PrincipalDetail;
import site.metacoding.blogproject.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({ "", "/" })
    public String index(Model model) {
        // 스프링에서는 데이터를 가져갈때 Model이 필요하다.
        model.addAttribute("boards", boardService.글목록());
        return "index"; // viewResolver 작동!! 해당 index페이지로(뷰) Model의 정보를 들고 이동한다.(글목록)
    }

    // USER 권한이 필요
    @GetMapping("/board/writeForm")
    public String writeForm() {
        return "board/writeForm";
    }
}
