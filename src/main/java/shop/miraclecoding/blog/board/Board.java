package shop.miraclecoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.miraclecoding.blog.user.User;
import shop.miraclecoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    //@JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // user_id 에 있는 pk변수를 걸어버릴 것이다.

    @CreationTimestamp  // pc -> db (날짜주입)
    private Timestamp createdAt;

    public String getTime(){    // 머스테치에 time을 쓰면 된다.
        return MyDateUtil.timestampFormat(createdAt);
    }

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }
}
