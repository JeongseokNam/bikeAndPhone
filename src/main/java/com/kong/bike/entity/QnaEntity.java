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
public class QnaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaId;

    @ManyToOne
    @JoinColumn(name = "question_member_id")
    private MemberEntity questionMemberEntity;

    @ManyToOne
    @JoinColumn(name = "answer_member_id")
    private MemberEntity answerMemberEntity;

    @Column
    private String qnaTitle;

    @Column
    private String questionContent;

    @Column
    private String answerContent;

    @Column
    private String answerYn;

    @Column
    private String delYn;

    @Column
    private LocalDateTime questionTime;

    @Column
    private LocalDateTime answerTime;

    public String getFormattedQuestionTime() {
        if (questionTime == null) {
            return "";
        }
        return questionTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
    public String getFormattedAnswerTime() {
        if (answerTime == null) {
            return "";
        }
        return answerTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}