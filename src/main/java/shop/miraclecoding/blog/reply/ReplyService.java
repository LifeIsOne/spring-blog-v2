package shop.miraclecoding.blog.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyJPARepository replyJPARepository;
}
