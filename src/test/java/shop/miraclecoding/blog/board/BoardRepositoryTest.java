package shop.miraclecoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    private EntityManager em;

    @Test
    public void updateById_test(){
        // given
        int id = 1;
        String title = "🍟Title🍟";
        String content = "🍟Content🍟";

        // when
        boardRepository.updateById(id, title, content);
        em.flush(); // 실제 코드에는 사용하지 않음.! / 안써도 실행됨 왜지

        // then
        System.out.println(boardRepository.findAll());
    }
    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        boardRepository.deleteById(id); // delte query 발동

        // then
        System.out.println("deleteById_test : " + boardRepository.findAll().size());
    }
    @Test
    public void findAllV2_test(){
        List<Board> boardList = boardRepository.findAllV2();
        System.out.println("findAllV2_test : 조회완료 쿼리 2번");
        boardList.forEach(board -> {
            System.out.println(board);
        });
    }

    @Test
    public void randomquery_tset(){
        int [] ids = {1,2};

        // SELECT u FROM User u WHERE u.id IN (?,?);
        String q = "SELECT u FROM User u WHERE u.id IN (";
        for (int i = 0; i < ids.length; i++) {
            if (i==ids.length-1){
                q = q + "?)";
            }else{
                q = q + "?,";
            }
        }
        System.out.println(q);
    }

    @Test
    public void findAll_custom_inquery_test() {
        List<Board> boardList = boardRepository.findAll();

        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        for (int i : userIds){
            System.out.println(i);
        }

        // select * from user_tb where id in (3,2,1,1);
        // select * from user_tb where id in (3,2,1);
    }

    @Test
    public void findAll_LazyLoading_test(){
        // give


        // when
        List<Board> boardList = boardRepository.findAll();

        // then
    }
    @Test
    public void findAll_test(){
        // give


        // when
        boardRepository.findAll();

        // then
    }

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
