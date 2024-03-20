package shop.miraclecoding.blog.user;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private final UserService userService;

    // TODO : 회원정보 조회 API 필요

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.회원수정(sessionUser.getId(), reqDTO);
        session.setAttribute("sessionUser",newSessionUser);

        return "redirect:/";
    }

    @PostMapping("/join")
    public String join (UserRequest.JoinDTO reqDTO){
//        userService.userUpdate(reqDTO.toEntity());

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO){
        User sessionUser = userService.로그인(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
