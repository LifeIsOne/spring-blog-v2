package shop.miraclecoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;

//    @Transactional
//    public void deleteByIdV2(int id){
//        Board board = findById(id);
//        em.remove(board);   // PC에 객체를 지우고, 트랜잭션 종료시 삭제 쿼리를 전송한다.
//    }   // commit

    @Transactional
    public void deleteById(int id){
        Query query = em.createQuery("DELETE FROM Board b WHERE b.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    public Board findById(int id){
        Board board = em.find(Board.class, id);   // PC의
        return null;
    }

    public List<Board> findAll(){
        Query query = em.createQuery("SELECT b FROM Board b ORDER BY b.id DESC", Board.class);
        return query.getResultList();
    }

    @Transactional
    public Board save(Board board) {
        // 비영속 객체
        em.persist(board);
        // board -> 영속 객체
        return board;
    }

    @Transactional
    public void updateById(int id, String title, String content, String username) {
        Query query =
                em.createNativeQuery("update board_tb set title=?, content=?, username=? where id=?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);
        query.setParameter(4, id);

        query.executeUpdate();
    }

}