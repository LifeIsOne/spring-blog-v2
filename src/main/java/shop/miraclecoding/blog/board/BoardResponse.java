package shop.miraclecoding.blog.board;

import lombok.Data;

public class BoardResponse {

    @Data
    public static class MainDTO {
        private int id;
        private String title;

        public MainDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }
}