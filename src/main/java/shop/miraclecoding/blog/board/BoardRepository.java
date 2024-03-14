package shop.miraclecoding.blog.board;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.miraclecoding.blog.user.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAllV3(){
        String q1 = "select b from Board b order by b.id desc";
        List<Board> boardList = em.createQuery(q1 , Board.class).getResultList();

        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();

        String q2 = "select u from User u where u.id in (";
        for (int i = 0; i < userIds.length ; i++) {
            if (i == userIds.length - 1){
                q2 = q2 + userIds[i] + ")";
            }else {
                q2 = q2 + userIds[i] + ",";
            }
        }
        List<User> userList = em.createQuery(q2 , User.class).getResultList();

        for (Board board : boardList){
            for (User user : userList) {
                if (user.getId() == board.getUser().getId()){
                    board.setUser(user);
                }
            }
        }

        return boardList; // user가 채워져 있어야함.
    }

    public List<Board> findAllV2() {
        Query q1 = em.createQuery("select b from Board b order by b.id desc", Board.class);
        List<Board> boardList = q1.getResultList();

        Set<Integer> userIds = new HashSet<>();
        for (Board board : boardList){
            userIds.add(board.getUser().getId());
        }

        Query q2 = em.createQuery("select u from User u where u.id in :userIds", User.class);
        q2.setParameter("userIds", userIds);
        List<User> userList = q2.getResultList();

        for (Board board : boardList){
            for (User user : userList) {
                if (user.getId() == board.getUser().getId()){
                    board.setUser(user);
                }
            }
        }

        return boardList;
    }
    public List<Board> findAll(){
        Query query = em.createQuery("SELECT b FROM Board b ORDER BY b.id DESC", Board.class);
        return query.getResultList();
    }

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
