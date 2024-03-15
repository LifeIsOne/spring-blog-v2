package shop.miraclecoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.miraclecoding.blog.board.Board;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em; // DI

    @Transactional
    public User updateById(Integer id, String password, String email) {
        User user = findById(id);
        user.setPassword(password);
        user.setEmail(email);

        return user;
    }   // 더티채킹

    public User findById(int id){
        User user = em.find(User.class, id);
        return user;
    }

    @Transactional
    public User save(User user){
        em.persist(user);
        return user;
    }

    public User findByUsernameAndPassword(String username, String password){
        Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username and u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        return (User) query.getSingleResult();
    }
}