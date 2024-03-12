package shop.miraclecoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import shop.miraclecoding.blog.util.MyDateUtil;

import java.sql.Timestamp;


@Data
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;
    private Timestamp createdAt;


}
