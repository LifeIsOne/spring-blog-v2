package shop.miraclecoding.blog.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.miraclecoding.blog._core.errors.exception.Exception400;
import shop.miraclecoding.blog._core.errors.exception.Exception401;
import shop.miraclecoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@Service    // IoC등록
@RequiredArgsConstructor
public class UserService {

    private final UserJPARepository userJPARepository;

    public UserResponse.DTO 회원조회(int id){
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다"));
        return new UserResponse.DTO(user);
    }

    @Transactional
    public User 회원수정(int id, UserRequest.UpdateDTO reqDTO){
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));

        // Dirty Checking
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());
        return user;
    }


    // 조회이기 때문에 @Transactional을 안붙여도 된다.
    public User 로그인(UserRequest.LoginDTO reqDTO){
        User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));    // orElse 값이 null이면 Throw
        return sessionUser;
    }

    @Transactional
    public User 회원가입(UserRequest.JoinDTO reqDTO){
        // 1. 유효성 검사 (Controller responsibility)

        // 2. username 중복검사 (Service 체크) - DB connection required
        Optional<User> userOp = userJPARepository.findByUsername(reqDTO.getUsername());

        if (userOp.isPresent())
            throw new Exception400("중복된 username입니다.");

        return userJPARepository.save(reqDTO.toEntity());
    }
}
