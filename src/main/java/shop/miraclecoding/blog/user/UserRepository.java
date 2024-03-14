package shop.miraclecoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public User findByUsernameAndPassword(UserRequest.LoginDTO loginDTO){
        Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username and u.password = :password");
        query.setParameter("username", loginDTO.getUsername());
        query.setParameter("password", loginDTO.getPassword());
        return (User) query.getSingleResult();
    }
}