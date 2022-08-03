package com.kong.bike.config;

import com.kong.bike.config.auth.PrincipalDetails;
import com.kong.bike.config.pageView.PageViewService;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.PageViewEntity;
import groovy.util.logging.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AspectConfig {

    @Autowired
    PageViewService pageViewService;
    public @interface MakerAnnotation {
    }

    @Pointcut("@annotation(AspectConfig.MakerAnnotation)")
    private void pointcut() {
    }

    @Before("pointcut()")
    public void logging(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String refer = request.getHeader("referer");
        String URL = request.getRequestURI();
        String userAgent = request.getHeader("User-Agent");

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        //System.out.println("> Result : IP Address : " + ip);

        String OS = "";

        if (userAgent.indexOf("Android") > 0) { //
            // IE
            OS = "Android";
        } else if (userAgent.indexOf("iPhone") > 0) {
            // Edge
            OS = "iPhone";
        } else {
            // Naver Whale
            OS = "web";
        }


        String browser = "";
        if (userAgent.indexOf("Trident") > -1) { //
            // IE
            browser = "ie";
        } else if ((userAgent.indexOf("Chrome") > -1) && (userAgent.indexOf("Edg") > -1)) {
            // Edge
            browser = "edge";
        } else if (userAgent.indexOf("Whale") > -1) {
            // Naver Whale
            browser = "whale";
        } else if (userAgent.indexOf("Opera") > -1 || userAgent.indexOf("OPR") > -1) {
            // Opera
            browser = "opera";
        } else if (userAgent.indexOf("Firefox") > -1) {
            // Firefox
            browser = "firefox";
        } else if (userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1) {
            // Safari
            browser = "safari";
        } else if (userAgent.indexOf("Chrome") > -1) {
            // Chrome
            browser = "chrome";
        } else {
            browser = "unknown";
        }

        PageViewEntity pageViewEntity = new PageViewEntity();

        pageViewEntity.setPageUrl(URL);
        pageViewEntity.setBeforeUrl(refer);
        pageViewEntity.setBrwsrInfo(browser);
        pageViewEntity.setOsInfo(OS);
        pageViewEntity.setIp(ip);
        LocalDateTime today = LocalDateTime.now();
        pageViewEntity.setRegDttm(today);
        pageViewService.save(pageViewEntity);
    }
}
