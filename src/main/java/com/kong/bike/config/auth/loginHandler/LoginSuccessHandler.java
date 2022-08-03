package com.kong.bike.config.auth.loginHandler;

import com.kong.bike.config.auth.PrincipalDetails;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    MemberService memberService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        List<String> roleNames = new ArrayList<>();
        authentication.getAuthorities().forEach(authority->{
            roleNames.add(authority.getAuthority());
        });
        Object principal = authentication.getPrincipal();
        PrincipalDetails userDetails = (PrincipalDetails)principal;
        MemberEntity memberEntity = userDetails.getMemberEntity();


        if(roleNames.contains("ROLE_MANAGER")) {
            response.sendRedirect("/");
        }
        if(roleNames.contains("ROLE_USER")) {
            response.sendRedirect("/");
        }
        if (roleNames.contains("ROLE_BUSINESS")){
            if ("Y".equals(memberEntity.getApprovalYn())){
                response.sendRedirect("/");
            }else {
                try {
                    response.setContentType("text/html; charset=utf-8");
                    PrintWriter w = response.getWriter();
                    String msg = "승인대기 중인 회원입니다.";
                    String url = "/member/logout";
                    w.write("<script>alert('"+msg+"');location.href='"+url+"';</script>");
                    w.flush();
                    w.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}