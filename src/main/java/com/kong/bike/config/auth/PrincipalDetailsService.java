package com.kong.bike.config.auth;

import com.kong.bike.entity.MemberEntity;
import com.kong.bike.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

// 시큐리티 설정에서 loginProcessingUrl("/login")
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 loC 되어 있는 loadUserByUsername 함수가 실행
@Service
@RequiredArgsConstructor
@Component
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberService memberService;

    private final HttpSession session;

    // 스프링이 로그인요청을 가로챌 때, username, password 변수 2개를 가로채는데
    // password 부분 처리는 알아서 함
    // username이 db에 있는지만 확인하면 됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity memberEntity = memberService.findByUsernameAndDelYn(username);
        if (memberEntity != null) {
            session.setAttribute("userName", memberEntity);
            return new PrincipalDetails(memberEntity);
        }
        return null;
    }
}

