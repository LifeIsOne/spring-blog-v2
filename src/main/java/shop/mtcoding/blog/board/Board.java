package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "board_tb")
@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String username;
    private Timestamp createdAt;
}
