package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;


    @PostMapping("/board/save")
    public String saveBoard(String username, String title, String content, HttpServletRequest request){
        boardRepository.saveBoard(title, content, username);

        request.setAttribute("boardList",);

        return "redirect:/";
    }

    @GetMapping( "/")
    public String index() {
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id) {    // Integer쓰는 이유 null값 받기 위해
        return "board/detail";
    }
}
