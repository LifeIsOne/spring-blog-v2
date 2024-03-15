package shop.miraclecoding.blog.user;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.miraclecoding.blog._core.errors.exception.Exception400;
import shop.miraclecoding.blog._core.errors.exception.Exception401;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final HttpSession session;
    private final UserRepository userRepository;

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userRepository.updateById(sessionUser.getId(), reqDTO.getPassword(),reqDTO.getEmail());
        session.setAttribute("sessionUser",newSessionUser);

        return "redirect:/";
    }

    @PostMapping("/join")
    public String join (UserRequest.JoinDTO reqDTO){
        try {
            userRepository.save(reqDTO.toEntity());
        } catch (NoResultException e) {
            throw new Exception400("중복된 username입니다.");
        }
        return "redirect:/";
    }
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO){
        try {
            User sessionUser = userRepository.findByUsername(reqDTO.getUsername());
            session.setAttribute("sessionUser", sessionUser);
            return "redirect:/";
        } catch (Exception e) {
            throw new Exception401("username또는 password가 틀렸습니다.");
        }
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
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        User user = userRepository.findById(sessionUser.getId());
        request.setAttribute("user", user);
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
