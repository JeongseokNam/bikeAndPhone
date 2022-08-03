package com.kong.bike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BikeBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId; //게시판 아이디

    @Column
    private String title; //제목

    @Column
    private String location; //장소

    @Column
    private String locationDetail; //상세장소

    @Column
    private String bikeBrand; //상표

    @Column
    private String bikeModel; //기종

    @Column
    private String bikeYear; //년식

    @Column
    private int driveKm; //주행거리

    @Column
    private String bikeColor; //색상

    @Column
    private String bikeGear; //수동 자동 기어

    @Column
    private String bikeCc; //배기량

    @Column
    private String changeAgree; //대차가능여부

    @Column
    private String negoAgree; //네고 가능여부

    @Column
    private String tuningYn; //튜닝여부

    @Column
    private String accidentYn; //사고여부

    @Column
    private String afterServiceYn; //AS여부

    @Column
    private String price; //가격

    @Column
    private String tuningOptions1; //추가옵션리스트1

    @Column
    private String tuningOptions2; //추가옵션리스트2

    @Column
    private String tuningOptions3; //추가옵션리스트3

    @Column
    private String documents; //서류리스트

    @Lob // 대용량 데이터
    private String content; //글내용

    @Column
    private int viewCount;//조회수

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
}
