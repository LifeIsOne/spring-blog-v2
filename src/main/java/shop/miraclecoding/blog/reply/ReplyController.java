package shop.miraclecoding.blog.reply;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
}
