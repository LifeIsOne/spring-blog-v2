package shop.miraclecoding.blog.reply;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.miraclecoding.blog.user.User;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/api/replies/")
    public String save(ReplyRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.댓글쓰기(reqDTO, sessionUser);
        return "redirect:/board/"+reqDTO.getBoardId();
    }

    @DeleteMapping("/api/replies/{id}")
    public String delete(@PathVariable Integer id){
        User sessionUser = (User) session.getAttribute(session.getId());
        replyService.댓글삭제(id, sessionUser.getId());
        return "redirect:/board";
    }
}
