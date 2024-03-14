package shop.miraclecoding.blog.board;

import lombok.Data;
import shop.miraclecoding.blog.user.User;

public class BoardRequest {


    @Data
    public static class SaveDTO{
        private String title;
        private String content;

        // DTO를 클라이언트로부터 받아서, PC에 전달하기 위해!!!!
        public Board toEntity(User user){
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(user)
                    .build();
        }

    }
}
