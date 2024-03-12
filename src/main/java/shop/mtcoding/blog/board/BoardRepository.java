package shop.mtcoding.blog.board;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public void saveBoard(String title, String content, String username){
        String q = """
                INSERT INTO board_tb(title, content, username, created_at) 
                VALUES      (?,?,?,now())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);

        query.executeUpdate();
    }
}
