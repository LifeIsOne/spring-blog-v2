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
    private final BoardPersistRepository boardPersistRepository;

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request){
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "board/update-form";
    }
    // 수정하기 컨트롤러
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, String title, String content, String username){
        boardNativeRepository.updateById(id, title, content, username);
        return "redirect:/board/"+id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id){
        boardNativeRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "board/detail";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO){
        boardPersistRepository.save(reqDTO.toEntity());

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

}
