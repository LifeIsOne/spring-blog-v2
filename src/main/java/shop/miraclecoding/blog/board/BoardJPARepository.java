package shop.miraclecoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    @Query("SELECT b FROM Board b JOIN FETCH b.user u WHERE b.id = :id")
    Optional<Board> findByIdJoinUser(@Param("id") int id);

    @Query("SELECT b FROM Board b JOIN FETCH b.user u LEFT JOIN FETCH b.replies r WHERE b.id = :id")
    Optional<Board> findByIdJoinUserAndReplies(@Param("id") int id);

    @Query("SELECT b FROM Board b JOIN FETCH b.user LEFT JOIN FETCH b.replies r JOIN FETCH r.user ru where b.id = :id")
    Board findDetail(@Param("id") int id);
}
