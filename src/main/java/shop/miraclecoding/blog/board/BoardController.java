package shop.miraclecoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.miraclecoding.blog._core.errors.exception.Exception400;
import shop.miraclecoding.blog._core.errors.exception.Exception401;
import shop.miraclecoding.blog._core.errors.exception.Exception403;
import shop.miraclecoding.blog._core.errors.exception.Exception404;
import shop.miraclecoding.blog.user.User;
import shop.miraclecoding.blog.user.UserRequest;

import java.io.Writer;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final HttpSession session;
    private final BoardService boardService;

    // Sever-Side Rendering은 DTO를 굳이 만들 필요가 없습니다.
    // 필요한 데이터만 Rendering해서 Client한테 전달하면 된다.
    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.글상세보기(id, sessionUser);

        request.setAttribute("board", board);
        System.out.println("SSR 직전에는 Board와 User만 조회된 상태입니다.");
        return "board/detail";
    }

    @GetMapping("/" )
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardService.글목록조회();
        request.setAttribute("boardList", boardList);
        return "index";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글쓰기(reqDTO, sessionUser);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");

        boardService.글삭제(id, sessionUser.getId());

        return "redirect:/board/"+id;
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardService.글조회(id);
        request.setAttribute("board", board);
        return "board/update-form";
    }

    @GetMapping("/hello")
    public String hello(){
        return "err/400";
    }


    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id, sessionUser.getId());
        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

}
