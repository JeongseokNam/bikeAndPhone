package com.kong.bike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MemberEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    private String username; //로그인시 입력하는 id

    @Column
    private String nickName; //닉네임

    @Column
    private String realName; //실명

    @Column
    private String email; //이메일
    @Column
    private String password; //비밀번호

    @Column
    private String phone; //휴대폰번호

    @Column
    private String emailConsent;//이메일 수신동의 여부

    @Column
    private String messageConsent;//문자메세지 수신동의 여부

    @Column
    private String memberRole; //권한

    @Column
    private String delYn; //탈퇴여부

    @Column
    private String delDttm; //탈퇴날짜

    @Column
    private String businessName; //상호명
    @Column
    private String ceoName; //대표자명
    @Column
    private String businessNum; //사업자등록번호
    @Column
    private String corporateNum; //법인등록번호
    @Column
    private String businessLocation; //사업장소재지
    @Column
    private String businessCondition; //사업종류-업태
    @Column
    private String businessItem; //사업종류-종목
    @Column
    private String soleOrCorporate;//개인 or 법인
    @Column
    private String approvalYn; //승인여부

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime modifiedDate;

    @Column
    private LocalDateTime recentAccess;

    public String getFormattedCreateDate() {
        if (createDate == null){
            return "";
        }
        return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

    public String getFormattedModifiedDate() {
        if (modifiedDate == null){
            return "";
        }
        return modifiedDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

    public String getFormattedRecentAccessDate() {
        if (recentAccess == null){
            return "";
        }
        return recentAccess.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
