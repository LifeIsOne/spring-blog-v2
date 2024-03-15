package shop.miraclecoding.blog.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class UpdateDTO {
        private String username;
        private String password;
        private String email;
    }

        @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

        public User toEntity(){
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }
    }
    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }
}
