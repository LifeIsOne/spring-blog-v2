package shop.miraclecoding.blog.board;

import lombok.Data;
import shop.miraclecoding.blog.user.User;

public class BoardResponse {

    @Data
    public static class DetailDTO{
        private Integer id;
        private String title;
        private String content;
        private UserDTO user;
        private Boolean isAuthor;   // 얘를 받기 위해 만듬.,

        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.user = new UserDTO(board.getUser());

            this.isAuthor = false;
            if (sessionUser != null){
                if (sessionUser.getId() == board.getUser().getId()){
                    isAuthor = true;
                }
            }
        }

        @Data
        public class UserDTO {
            private int id;
            private String username;

            public UserDTO(User user) {
                this.id = user.getId();
                this.username = user.getUsername();
            }
        }
    }
}
