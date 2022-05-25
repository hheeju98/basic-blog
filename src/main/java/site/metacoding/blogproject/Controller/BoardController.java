package site.metacoding.blogproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import site.metacoding.blogproject.domain.BoardRepository;
import site.metacoding.blogproject.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;

    @GetMapping({ "", "/" })
    public String index(@RequestParam(defaultValue = "0") Integer page, Model model) {
        // 1. postRepository의 findAll() 호출
        // 2. model에 담기
        // model.addAttribute("posts",
        // postRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
        PageRequest pq = PageRequest.of(page, 3);
        model.addAttribute("boards", boardRepository.findAll(pq));
        model.addAttribute("prevPage", page - 1);
        model.addAttribute("nextPage", page + 1);

        return "index";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("boards", boardService.글상세보기(id));
        boardService.글상세보기(id);
        return "board/detail";
    }

    /**
     * @GetMapping({ "", "/" })
     * public String index(Model model,
     * 
     * //@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)
     * Pageable pageable) {
     * // 스프링에서는 데이터를 가져갈때 Model이 필요하다.
     * model.addAttribute("boards",
     * boardService.글목록(pageable));
     * return "index"; // viewResolver 작동!! 해당 index페이지로(뷰)
     * Model의 정보를 들고 이동한다.(글목록)
     * }
     */

    // USER 권한이 필요
    @GetMapping("/board/writeForm")
    public String writeForm() {
        return "board/writeForm";
    }
}
