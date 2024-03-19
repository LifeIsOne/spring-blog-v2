package shop.miraclecoding.blog.reply;

import lombok.Data;
import shop.miraclecoding.blog.board.Board;
import shop.miraclecoding.blog.user.User;

public class ReplyRequest {

    @Data
    public static class SaveDTO{
        private Integer boardId;
        private String comment;

        public Reply toEntity (User sessionUser, Board board){
            return Reply.builder()
                    .comment(comment)
                    .board(board)
                    .user(sessionUser)
                    .build();
        }
    }
}
