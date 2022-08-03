package com.kong.bike.DTO;

import com.kong.bike.comment.service.CommentService;
import com.kong.bike.entity.BikeBoardEntity;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.PhoneBoardEntity;
import com.kong.bike.entity.ReCommentEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class CommentListDTO {
    @Autowired
    CommentService commentService;
    private Long commentId; //댓글id

    private MemberEntity memberEntity; //참조 작성자

    private List<ReCommentEntity> reCommentEntityList; //대댓 리스트

    private String content; //내용

    private String delYn; //삭제여부

    private LocalDateTime modifiedDate;//작성일

    private String privateYn; //비밀여부

    //날짜포멧
    public String getFormattedModifiedDate() {
        if (modifiedDate == null){
            return "";
        }
        return modifiedDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
