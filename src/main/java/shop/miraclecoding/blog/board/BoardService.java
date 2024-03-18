package shop.miraclecoding.blog.board;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.miraclecoding.blog._core.errors.exception.Exception403;
import shop.miraclecoding.blog._core.errors.exception.Exception404;
import shop.miraclecoding.blog.user.User;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardJPARepository boardJPARepository;

    public Board 글상세보기(int boardId, User sessionUser) {
        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        boolean isOwner = false;
        if(sessionUser != null){
            if(sessionUser.getId() == board.getUser().getId()){
                isOwner = true;
            }
        }

        board.setOwner(isOwner);

        return board;
    }

    public List<Board> 글목록조회() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return boardJPARepository.findAll();
    }

    @Transactional
    public void 글삭제(Integer boardId, Integer sessionUserId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        if (sessionUserId != board.getUser().getId()){
            throw new Exception403("게시글을 삭제할 권한이 없습니다.");
        }
        boardJPARepository.deleteById(boardId); // 같은 트랙잭션에 묶여있으므로, 순서가 바뀌여도 된다. (Runtimeexception)
    }

    public Board 글조회 (int boardId){
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        return board;
    }

    @Transactional
    public void 글수정 (int boardId, int sessionUserId, BoardRequest.UpdateDTO reqDTO){
        // 1. 조회 및 예외처리
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        // 2. 권한 처리 . DB가 연결이 되야한다. Service에서 해야한다
        if (sessionUserId != board.getUser().getId()){
            throw new Exception403("게시글을 수정할 권한이 없습니다!");
        }

        // 3. 게시글 수정
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());
    }   // dirty checking

    @Transactional
    public void 글쓰기(BoardRequest.SaveDTO reqDTO, User sessionUser){
        boardJPARepository.save(reqDTO.toEntity(sessionUser));
    }

}