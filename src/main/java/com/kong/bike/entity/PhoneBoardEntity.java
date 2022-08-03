package com.kong.bike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.Query;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId; //게시판 아이디

    @Column
    private String title; //제목

    @Lob // 대용량 데이터
    private String content; //글내용

    @Column
    private String location; //판매지역

    @Column
    private String dealWay; // 거래방법

    @Column
    private String inquiry; // 상품문의

    @Column
    private String phoneYn; // 연락처 공개여부

    @Column
    private int viewCount; //조회수

    @Column
    private String price; //가격

    @Column
    private String delYn;//삭제여부

    @Column
    private String sellYn;//판매여부

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime modifiedDate;

    public String getFormattedModifiedDate() {
        if (modifiedDate == null){
            return "";
        }
        return modifiedDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }
}
