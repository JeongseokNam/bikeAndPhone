package com.kong.bike.comment.service;

import com.kong.bike.DTO.CommentListDTO;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.ReCommentEntity;

import java.util.List;

public interface CommentService {
    void addComment(Long relatId, Long memberId, String content, String privateYn,String relatDiv);

    List<CommentListDTO> getCommentList(Long relatid,String relatDiv);

    void delComment(Long commentId);

    List<ReCommentEntity> getReCommentList(Long commentId);

    void delReComment(Long commentId);

    void addReComment(MemberEntity memberEntity, Long relatId, String content, String privateYn, String targetNickName);
}
