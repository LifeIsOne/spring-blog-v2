package shop.miraclecoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.miraclecoding.blog._core.utils.ApiUtil;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final HttpSession session;
    private final UserService userService;

    // TODO : 회원정보 조회 API 필요 -> @GetMapping("/api/users/{id}")
    @GetMapping("/users/{id}")
    public ResponseEntity<?> userinfo(@PathVariable Integer id){
        UserResponse.DTO respDTO = userService.회원조회(id);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> update (@PathVariable Integer id, @RequestBody UserRequest.UpdateDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.회원수정(sessionUser.getId(), reqDTO);
        session.setAttribute("sessionUser",newSessionUser);

        return ResponseEntity.ok(new ApiUtil(newSessionUser));
    }

    @PostMapping("/join")
    public ResponseEntity<?> join (@RequestBody UserRequest.JoinDTO reqDTO){
        User user = userService.회원가입(reqDTO);
        return ResponseEntity.ok(new ApiUtil(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(UserRequest.LoginDTO reqDTO){
        User sessionUser = userService.로그인(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
        return ResponseEntity.ok(new ApiUtil(null));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        session.invalidate();
        return ResponseEntity.ok(new ApiUtil(null));
    }
}
