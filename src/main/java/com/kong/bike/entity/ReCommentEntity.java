package com.kong.bike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ReCommentEntity extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId; //댓글id

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity; //참조 작성자 id

    @Column Long relatId;

    @Lob // 대용량 데이터
    private String content; //내용

    @Column
    private String delYn; //삭제여부

    @Column
    private String privateYn; //비밀여부

    @Column
    private String targetNickname; //대상닉네임

}
