package shop.miraclecoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;

public class BoardPersistRepository {

    private final BoardPersistRepository boardPersistRepository;

    @Test
    public void save_test(){
        // given
        Board board = new Board("제목7","내용7","Daily");

        // when
        boardPersistRepository.save(board);
    }
}
