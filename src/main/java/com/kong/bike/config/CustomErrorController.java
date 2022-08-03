package com.kong.bike.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
    @GetMapping("/error")
    public String errorHandler(HttpServletRequest request){
        Object status= request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null){
            int statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.FORBIDDEN.value()){
                return "redirect:/";
            }
        }
        return "error";
    }
}
