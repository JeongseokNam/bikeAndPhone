package com.kong.bike.config;

import com.kong.bike.config.auth.PrincipalDetailsService;
import com.kong.bike.config.auth.loginHandler.LoginFailureHandler;
import com.kong.bike.config.auth.loginHandler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록된다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // 컨트롤러-secured 어노테이션 활성화, preAuthorize 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailsService principalDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder encoderPwd(){
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailsService).passwordEncoder(encoderPwd());
    }
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers(
                "/css/**",
                "/image/**",
                "/js/**"
        );
    }

    @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.authorizeRequests()
                    .antMatchers("/board/addPost",
                            "/member/myPage",
                            "/member/myPage/**",
                            "/member/remove",
                            "/board/soldOut",
                            "/board/soldOutCancel",
                            "/board/modify/**",
                            "/file/upload",
                            "/file/update",
                            "/member/changePhone/**",
                            "/member/PwCheck",
                            "/board/delete/**",
                            "/qna/**").authenticated()
                    .antMatchers("/manager/**").access("hasRole('ROLE_MANAGER')")
                    .anyRequest().permitAll() // 나머지는 권한없이 접근가능 (authenticated랑 permitAll 바꿔서도 사용가능)
                    .and()
                    .formLogin()
                    .loginPage("/member/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successHandler(new LoginSuccessHandler())
                    .failureHandler(new LoginFailureHandler())
                    .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .and()
                    .csrf().disable();

//                    .and()
//                    .oauth2Login()
//                    .userInfoEndpoint()
//                    .JoinService(customOAuth2UserService);

        }
}
