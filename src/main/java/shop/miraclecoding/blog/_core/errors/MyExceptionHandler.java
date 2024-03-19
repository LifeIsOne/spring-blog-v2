package shop.miraclecoding.blog._core.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shop.miraclecoding.blog._core.errors.exception.*;

@ControllerAdvice   // RuntimeException이 터지면 해당 파일로 오류가 모인다.
public class MyExceptionHandler {   // error만 관리하는 컨트롤러

    @ExceptionHandler(Exception400.class)
    public String ex400(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/400";
    }

    @ExceptionHandler(Exception401.class)
    public String ex401(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/401";
    }

    @ExceptionHandler(Exception403.class)
    public String ex403(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/403";
    }

    @ExceptionHandler(Exception404.class)
    public String ex404(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/404";
    }

    @ExceptionHandler(Exception500.class)
    public String ex500(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/500";
    }
//    실제론 필요하지만 오늘은 하지말자
//    @ExceptionHandler(Exception.class)
//    public String exUnknown(Exception e){
//        // DB에러 로그 남기기
//        // 관리자 연락하기
//        // 이메일 보내기
//        return "err/unknown;
//    }
}
