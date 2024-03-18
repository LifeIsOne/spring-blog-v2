package shop.miraclecoding.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 저동 컴퍼넌트 스캔이 된다.
public interface UserJPARepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :username and u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password); // 추상 메서드 생성
}