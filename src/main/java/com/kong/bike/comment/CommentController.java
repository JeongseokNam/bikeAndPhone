package com.kong.bike.comment;

import com.kong.bike.comment.service.CommentService;
import com.kong.bike.config.auth.PrincipalDetails;
import com.kong.bike.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @PostMapping("/comment/addComment")
    public void addComment(Long relatId,Long memberId,String content,String privateYn,String relatDiv){
        /**
         * 댓글 추가
         * 메서드명 : addComment
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-21
        **/
        commentService.addComment(relatId,memberId,content,privateYn,relatDiv);
        HashMap<String,Integer> repo = new HashMap<>();
    }
    @ResponseBody
    @PostMapping("/comment/addReComment")
    public void addReComment(Long relatId, String content, String privateYn, String targetNickName,
                             @AuthenticationPrincipal PrincipalDetails principalDetails){
        MemberEntity memberEntity = principalDetails.getMemberEntity();
        commentService.addReComment(memberEntity,relatId,content,privateYn,targetNickName);
    }
    @ResponseBody
    @PostMapping("/comment/delComment")
    public void delComment(Long commentId){
        /**
         * 댓글 삭제
         * 메서드명 : delComment
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-21
         **/
        commentService.delComment(commentId);
    }
    @ResponseBody
    @PostMapping("/comment/delReComment")
    public void delReComment(Long commentId){
        commentService.delReComment(commentId);
    }
}
