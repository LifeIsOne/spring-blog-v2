package shop.miraclecoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardNativeRepository boardNativeRepository;

    @PostMapping("/board/save")
    public String save(String username, String title, String content){
        boardNativeRepository.save(title, content, username);

        return "redirect:/";
    }

    @GetMapping("/" )
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardNativeRepository.findAll();
        request.setAttribute("boardList", boardList);
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
