package shop.miraclecoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final HttpSession session;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO){
        User sessionUser = userRepository.findByUsernameAndPassword(reqDTO);

//        if (sessionUser == null){           // 로그인 하셔야죠?
//            return "redirec:/login-form";
//        }

        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @GetMapping("/user/update-form")
    public String updateForm() {
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
