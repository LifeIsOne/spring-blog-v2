package shop.miraclecoding.blog._core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shop.miraclecoding.blog._core.interceptor.LoginInterceptor;

@Configuration // IoC
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/api/**") // api는 인증이 필요 ⭕
                .excludePathPatterns("/api/boards/{id:\\d+}/detail");  // boards는 인증 필요 ❌
    }
}