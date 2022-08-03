package com.kong.bike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    @Column
    private String title;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "notice_member_id")
    private MemberEntity memberEntity;

    @Column
    private String delYn;

    @Column
    private LocalDateTime createDate;

    public String getFormattedCreateDate() {
        if (createDate == null) {
            return "";
        }
        return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
    @Column
    private LocalDateTime modifyDate;

    public String getFormattedModifyDate() {
        if (modifyDate == null) {
            return "";
        }
        return modifyDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }


}

