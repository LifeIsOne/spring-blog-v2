package shop.miraclecoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    @Query("SELECT b FROM Board b JOIN FETCH b.user u WHERE b.id = :id")
//    Optional<Board> findByIdJoinUser(@Param("id") int id);
    Board findByIdJoinUser(@Param("id") int id);

}
