package dongyang.ac.kr.greennaePro.config;


import dongyang.ac.kr.greennaePro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//보안 담당 클래스 스프링 시큐리티
@Configuration//시큐리티 필수 항목
@RequiredArgsConstructor
@EnableWebSecurity//시큐리티 필수 항목
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web)throws Exception{
        web.ignoring().antMatchers("/css/**","/js/**","/lib/**");
        //웹으로 들어오는 거중에 보안을 안거치는 파일들 css,js,lib,img는 무조건통과
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/login/signin","/login/signup", "/display/**").permitAll()//permitAll은 누구나 접근가능
                    .antMatchers("/","/room","/credit","/myprofile","/getmap").hasAnyRole("USER","MEMBER","ADMIN")//권한에 따라서 볼수있는 url이 다름
                    .antMatchers("/board/**").hasRole("MEMBER")
                    .antMatchers("/user/custom").hasRole("ADMIN")
                .and()
                .formLogin()
                    .loginPage("/login/signin")//로그인할 페이지
                    .loginProcessingUrl("/login_proc")
                    .defaultSuccessUrl("/")//로그인 성공시 보낼 페이지
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/doLogout")//로그아웃 url
                    .logoutSuccessUrl("/login/signin")// 로그아웃 성공시 보낼 페이지
                    .invalidateHttpSession(true)
                .and()
                .exceptionHandling();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("dongyang")
                .password("$2a$10$gOXZ7vxyaJC8fH9pdLiU8e5QpV2zfOcT6n0LzUfa4ckMclxJiB01.")
                .roles("USER","MEMBER","ADMIN");

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

    }
}
