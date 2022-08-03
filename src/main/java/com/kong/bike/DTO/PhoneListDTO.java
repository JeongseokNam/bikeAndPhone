package com.kong.bike.DTO;

import com.kong.bike.entity.MemberEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class PhoneListDTO {

    private Long phoneId; //게시판 아이디

    private String title; //제목

    private String location; //장소

    private String price; //가격

    private String content; //글내용

    private int viewCount;//조회수

    private String delYn;//삭제여부

    private String sellYn;//판매여부

    private LocalDateTime modifiedDate; // 작성일
    /*-------------파일---------------*/
    //저장파일명
    private String saveFileNm;
    //저장경로
    private String filePath;
    /*------------작성자--------------*/
    private MemberEntity memberEntity;


    public String getFormattedModifiedDate() {
        if (modifiedDate == null){
            return "";
        }
        return modifiedDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
