package shop.miraclecoding.blog.board;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

public Board findByIdJoinUser(int id){
    Query query = em.createQuery("SELECT b FROM Board b JOIN FETCH b.user u WHERE b.id = :id", Board.class);
    query.setParameter("id", id);
    return (Board) query.getSingleResult();
}

    public Board findById(int id){
        // id, title, content, user_id(이질감), created_at
        Board board = em.find(Board.class, id);

        return board;
    }
}
