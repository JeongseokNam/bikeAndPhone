package com.kong.bike.config;

import com.kong.bike.config.auth.PrincipalDetails;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    MemberService memberService;
    @GetMapping("/")
    @AspectConfig.MakerAnnotation
    public String indexView(@AuthenticationPrincipal PrincipalDetails principalDetails){
        MemberEntity memberEntity;
        if (principalDetails!=null){
            memberEntity=principalDetails.getMemberEntity();
            memberService.accessRecord(memberEntity.getMemberId());
        }
        return "/index";
    }
}
