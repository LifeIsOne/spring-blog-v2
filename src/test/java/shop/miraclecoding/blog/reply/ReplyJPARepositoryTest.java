package shop.miraclecoding.blog.reply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

/**
 *  1. One 관계는 JOIN, Many 관계는 SELECT x2 => DTO 담기
 *  2. Many관계 양방향 Mapping
 */

@DataJpaTest
public class ReplyJPARepositoryTest {

    @Autowired
    private ReplyJPARepository replyJPARepository;
    
    @Test
    public void findByBoardId_test(){
        // given
        int boardId = 4;
        
        // when
//        List<Reply> replyList = replyJPARepository.findById(boardId);
        
        // then
        System.out.println("findByIdJoinUser_test : ");
    }
}
