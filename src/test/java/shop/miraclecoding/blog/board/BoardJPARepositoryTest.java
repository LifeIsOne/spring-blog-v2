package shop.miraclecoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.miraclecoding.blog.user.User;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;

    // save
    @Test
    public void save_test(){
        // given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("Title5")
                .content("content5")
                .user(sessionUser)
                .build();

        // when
        boardJPARepository.save(board);

        // then
        System.out.println("save_test : " + board.getId()); // 영속객체가 되었기 때문에 아이디가 있는지 확인해보는 것
    }

    // findById
    @Test
    public void findById_test(){
        // given
        int id = 1;

        // when
        Optional<Board> boardOP = boardJPARepository.findById(id);

        if (boardOP.isPresent()){
            Board board = boardOP.get();
            System.out.println("findById_test: " + board.getTitle());
        }
        // then

    }

//    // findByIdJoinUser
//    @Test
//    public void findByIdJoinUser_test(){
//        // given
//        int id = 1;
//
//        // when
//        Board board = boardJPARepository.findByIdJoinUser(id);
//
//        // then
//        System.out.println("findByIdJoinUser_test : " + board.getTitle());
//        System.out.println("findByIdJoinUser_test : " + board.getUser().getUsername());
//    }

    // findAll (sort)
    @Test
    public void findAll_test(){
        // given
        Sort sort = Sort.by(Sort.Direction.DESC,"id");

        // when
        List<Board> boardList = boardJPARepository.findAll(sort);

        // then
        System.out.println("findAll_test : " + boardList);
    }
    // findAll (pageable)

    // deleteById
    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        boardJPARepository.deleteById(id);
        em.flush();
        // then

    }
}
