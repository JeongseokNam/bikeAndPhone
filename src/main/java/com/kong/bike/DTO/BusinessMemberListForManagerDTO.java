package com.kong.bike.DTO;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class BusinessMemberListForManagerDTO {
    
    
    private Long memberId;

    private String username; //로그인시 입력하는 id

    private String nickName; //닉네임
    
    private String realName; //실명

    private String email; //이메일

    private String phone; //휴대폰번호

    private String businessName; //상호명
    
    private String ceoName; //대표자명
    
    private String businessNum; //사업자등록번호
    
    private String corporateNum; //법인등록번호
    
    private String businessLocation; //사업장소재지
    
    private String businessCondition; //사업종류-업태
    
    private String businessItem; //사업종류-종목
    
    private String soleOrCorporate;//개인or법인
    
    private String approvalYn; //승인여부

    private LocalDateTime createDate;//가입일자

    public String getFormattedCreateDate() {
        if (createDate == null){
            return "";
        }
        return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

    //---------------------파일정보
    //저장파일명
    private String saveFileNm;
    //저장경로
    private String filePath;
}
