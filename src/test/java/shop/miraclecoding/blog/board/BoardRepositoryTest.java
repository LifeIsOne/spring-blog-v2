package shop.miraclecoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findByIdJoinUser(){
        int id = 1;
        boardRepository.findByIdJoinUser(id);

    }

    @Test
    public void findById_test(){
        int id = 1;
        System.out.println("1️1️1️1️1️1️1️1️️️️️️️1️1️1️1️1️1️1️1️️️️️️️");
        Board board = boardRepository.findById(id);
        System.out.println("2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️");
        System.out.println(board.getUser().getId());
        System.out.println("3️⃣3️⃣3️⃣3️⃣3️⃣3️⃣3️⃣3️⃣3️⃣3️⃣3️⃣3️⃣3️⃣3️⃣3️⃣3️⃣");
        System.out.println(board.getUser().getUsername());

    }
}
