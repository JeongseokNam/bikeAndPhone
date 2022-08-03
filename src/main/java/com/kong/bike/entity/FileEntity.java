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
public class FileEntity extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    //퍄일아이디
    @Column
    private int fileNum;

    //저장파일명
    @Column
    private String saveFileNm;

    //실제파일명
    @Column
    private String realFileNm;

    //저장 파일 경로
    @Column
    private String filePath;

    //파일확장자
    @Column
    private String fileExt;

    //파일크기
    @Column
    private long fileSize;

    //파일구분
    @Column
    private String fileDiv;

    //연관 아이디
    @Column
    private long relatId;

    //삭제여부
    @Column
    private String delYn;
}
