package com.kong.bike.DTO;

import com.kong.bike.entity.MemberEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class BoardListDTO {
    
    private Long boardId; //게시판 아이디

    private String title; //제목
    
    private String location; //장소
    
    private String locationDetail; //상세장소
    
    private String bikeBrand; //상표
    
    private String bikeModel; //기종
    
    private String bikeYear; //년식
    
    private int driveKm; //주행거리
    
    private String bikeColor; //색상

    private String bikeGear; //수동 자동 기어
    
    private String bikeCc; //배기량
    
    private String changeAgree; //대차가능여부
    
    private String negoAgree; //네고 가능여부
    
    private String tuningYn; //튜닝여부
    
    private String accidentYn; //사고여부
    
    private String afterServiceYn; //AS여부
    
    private String price; //가격
    
    private String tuningOptions1; //추가옵션리스트1
    
    private String tuningOptions2; //추가옵션리스트2

    private String tuningOptions3; //추가옵션리스트3
    
    private String documents; //서류리스트

    private String content; //글내용
    
    private int viewCount;//조회수
    
    private String delYn;//삭제여부
    
    private String sellYn;//판매여부

    private LocalDateTime modifiedDate;//작성일
    /*-------------파일---------------*/
    //저장파일명
    private String saveFileNm;
    //저장경로
    private String filePath;
    /*------------작성자--------------*/
    private Long memberId; //회원넘버

    private String nickName; //닉네임

    private MemberEntity memberEntity;


    public String getFormattedModifiedDate() {
        if (modifiedDate == null){
            return "";
        }
        return modifiedDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
