package com.kong.bike.config.auth.loginHandler;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    String MSG_USER_NOT_FOUND = "존재하지 않는 사용자입니다.";
    String MSG_WRONG_ID_OR_PASSWORD = "아이디 또는 비밀번호가 일치하지 않습니다.";
    String DEFAULT_FAILURE_URL = "/loginError";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = null;
        if(exception instanceof AuthenticationServiceException) {
            errorMsg = MSG_USER_NOT_FOUND;
        } else if(exception instanceof BadCredentialsException) {
            errorMsg = MSG_WRONG_ID_OR_PASSWORD;
        }

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<script>alert('로그인에 실패했습니다.'); location.href = '/member/login';</script>");

        out.flush();

        request.setAttribute("username", request.getParameter("username"));
        request.setAttribute("errorMsg", errorMsg);
        RequestDispatcher dispatcher = request.getRequestDispatcher(DEFAULT_FAILURE_URL);
        dispatcher.forward(request, response);
    }

}