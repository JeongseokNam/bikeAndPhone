package com.kong.bike.businessMember;

import com.kong.bike.businessMember.service.BusinessMemberService;
import com.kong.bike.config.FileStore;
import com.kong.bike.entity.FileEntity;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.file.service.FileService;
import com.kong.bike.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BusinessMemberController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FileStore fileStore;
    @Autowired
    private FileService fileService;
    @Autowired
    private BusinessMemberService  businessMemberService;
    @GetMapping("/member/soleProprietorJoin")
    public String soleProprietorJoinFrom(){
        return "/member/soleProprietorJoinForm";
    }
    @PostMapping("/member/soleProprietorJoin")
    public String soleProprietorJoin(MemberEntity memberEntity, MultipartFile file) {
        String tempPw = memberEntity.getPassword();
        String encPW = bCryptPasswordEncoder.encode(tempPw);
        memberEntity.setPassword(encPW);
        memberEntity.setSoleOrCorporate("sole");
        memberService.saveBusinessMember(memberEntity);
        int recentMemberId = memberService.getRecentMemberId();
        if (recentMemberId<=0){
            recentMemberId =1;
        }
        FileEntity fileEntity = fileStore.uploadFile(file,1,recentMemberId,"businessLicense");
        fileService.save(fileEntity);
        return "redirect:/member/joinSuccess?type=business";
    }
    @GetMapping("/member/corporateJoin")
    public String corporateJoinFrom(){
        return "/member/corporateJoinForm";
    }
    @PostMapping("/member/corporateJoin")
    public String corporateJoin(MemberEntity memberEntity, MultipartFile file) {
        String tempPw = memberEntity.getPassword();
        String encPW = bCryptPasswordEncoder.encode(tempPw);
        memberEntity.setPassword(encPW);
        memberEntity.setSoleOrCorporate("corporate");
        memberService.saveBusinessMember(memberEntity);
        int recentMemberId = memberService.getRecentMemberId();
        if (recentMemberId<=0){
            recentMemberId =1;
        }
        FileEntity fileEntity = fileStore.uploadFile(file,1,recentMemberId,"businessLicense");
        fileService.save(fileEntity);
        return "redirect:/member/joinSuccess?type=business";
    }
    @PostMapping("/member/businessMemberApproval")
    @ResponseBody
    public String changeApproval(Long memberId,String approvalYn){
        businessMemberService.changeApproval(memberId,approvalYn);
        if (approvalYn.equals("Y")){
            return "승인처리 되었습니다.";
        }else{
            return "승인취소처리 되었습니다.";
        }
    }
}
