package shop.miraclecoding.blog.reply;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.valves.rewrite.RewriteCond;
import org.springframework.stereotype.Service;
import shop.miraclecoding.blog._core.errors.exception.Exception403;
import shop.miraclecoding.blog._core.errors.exception.Exception404;
import shop.miraclecoding.blog.board.Board;
import shop.miraclecoding.blog.board.BoardJPARepository;
import shop.miraclecoding.blog.user.User;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyJPARepository replyJPARepository;
    private final BoardJPARepository boardJPARepository;

    @Transactional
    public Reply 댓글쓰기(ReplyRequest.SaveDTO reqDTO, User sessionUser) {
        Board board = boardJPARepository.findById(reqDTO.getBoardId())
                .orElseThrow(() -> new Exception404("없는 게시글에 댓글을 작성할 수 없어요"));

        Reply reply = reqDTO.toEntity(sessionUser, board);

        return replyJPARepository.save(reply);
    }

    @Transactional
    public void 댓글삭제(int replyId, int sessionUserId){
        Reply reply = replyJPARepository.findById(replyId)
                .orElseThrow(() -> new Exception404("댓글을 찾을 수 없습니다!"));

        if (reply.getId() != sessionUserId){
            throw new Exception403("댓글을 삭제할 권한이 없습니다.");
        }
        replyJPARepository.deleteById(replyId);
    }
}
