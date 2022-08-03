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
public class PageViewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pageViewId;

    @Column
    private String pageUrl; //접속주소

    @Column
    private String beforeUrl; //이전페이지 주소

    @Column
    private String ip;

    @Column
    private String osInfo; //운영체제정보

    @Column
    private String brwsrInfo; //브라우저 정보

    @Column
    private LocalDateTime regDttm; //등록일시
}
