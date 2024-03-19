package shop.miraclecoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardNativeRepository {
    private final EntityManager em;

    @Transactional
    public void save(String title, String content, String username){
        Query query =
                em.createQuery("INSERT INTO Board(title, content, username, created_at) VALUES (:title, :content, :username, now()");
        query.setParameter("title", title);
        query.setParameter("content", content);
        query.setParameter("username", username);

        query.executeUpdate();
    }

    @Transactional
    public void updateById(int id, String title, String content, String username) {
        Query query =
                em.createQuery("UPDATE Board b SET b.title = :title, b.content = :content, b.username = :username WHERE b.id = :id");
        query.setParameter("title", title);
        query.setParameter("content", content);
        query.setParameter("username", username);
        query.setParameter("id", id);

        query.executeUpdate();
    }

    @Transactional
    public void deleteById(int id) {
        Query query =
                em.createQuery("DELETE FROM Board WHERE id = :id");
        query.setParameter("id", id);

        query.executeUpdate();
    }

    public Board findById(int id) {
        Query query = em.createQuery("SELECT b FROM Board b WHERE b.id = :id", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }



    public List<Board> findAll(){
        Query query = em.createQuery("SELECT b FROM Board b ORDER BY b.id DESC", Board.class);
        return query.getResultList();
    }
}
