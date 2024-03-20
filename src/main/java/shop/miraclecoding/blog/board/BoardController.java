package shop.miraclecoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    // TODO : 글 상세보기 API 필요. @GetMapping("/")

    // TODO : 글 목록 조회 API 필요. @GetMapping("/api/boards/{id}/detail")

    // TODO : 글 조회 API 필요. @GetMapping("/api/boards/{id}")

    @PostMapping("/api/boards")
    public String save(BoardRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글쓰기(reqDTO, sessionUser);
        return "redirect:/";
    }

    @PutMapping("/api/boards/{id}")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");

        boardService.글수정(id, sessionUser.getId(),reqDTO);

        return "redirect:/board/"+id;
    }

    @DeleteMapping("/api/boards/{id}")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id, sessionUser.getId());
        return "redirect:/";
    }

    @GetMapping("/hello")
    public String hello(){
        return "err/400";
    }



    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

}
