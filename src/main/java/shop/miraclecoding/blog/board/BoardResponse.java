package shop.miraclecoding.blog.board;

import lombok.Data;
import shop.miraclecoding.blog.reply.Reply;
import shop.miraclecoding.blog.user.User;

import java.util.ArrayList;
import java.util.List;

public class BoardResponse {

    @Data
    public static class DTO {
        private int id;
        private String title;
        private String content;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }

    // 게시글 상세보기
    @Data
    public static class DetailDTO{
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username;
        private List<ReplyDTO> replies = new ArrayList<>(); // new를 하지 않으면 null.get하다가 터진다. 초기화 해주자.
        private boolean isOwner;

        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();  // join 해서 가져온다.

            this.isOwner = false;
            if (sessionUser != null){
                if (sessionUser.getId() == userId) isOwner = true;
            }

            this.replies = board.getReplies().stream().map(reply -> new ReplyDTO(reply, sessionUser)).toList();
        }

        @Data
        public class ReplyDTO{
            private int id;
            private String comment;
            private int userId;
            private String username;
            private boolean isOwner;

            public ReplyDTO(Reply reply, User sessionUser) {
                this.id = reply.getId();    // LAZY Loading 발동 (아이디 조차 없었으니)
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername();  // LAZY Loading 발동

                this.isOwner = false;
                if (sessionUser != null){
                    if (sessionUser.getId() == userId) isOwner = true;
                }
            }
        }
    }

    // 게시글 목록보기
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