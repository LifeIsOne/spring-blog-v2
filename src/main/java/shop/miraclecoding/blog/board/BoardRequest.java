package shop.miraclecoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO{
        private String title;
        private String content;
        private String username;

        public Board toEntity(){    // 엔티티로 바꾸는 메서드, INSERT할 때만 필요하다. 왜 Entity로 바꿨지? 비영속 객체이기 때문에
            return new Board(title, content, username);
        }
    }
}
