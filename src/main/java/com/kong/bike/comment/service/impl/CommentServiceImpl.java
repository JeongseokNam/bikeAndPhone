package com.kong.bike.comment.service.impl;

import com.kong.bike.DTO.CommentListDTO;
import com.kong.bike.comment.repository.CommentRepository;
import com.kong.bike.comment.repository.ReCommentRepository;
import com.kong.bike.comment.service.CommentService;
import com.kong.bike.entity.CommentEntity;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.ReCommentEntity;
import com.kong.bike.member.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ReCommentRepository reCommentRepository;
    @Override
    public void addComment(Long relatId, Long memberId, String content, String privateYn,String relatDiv) {
        /**
         * 댓글정보 entity 셋팅 후 save
         * 메서드명 : addComment
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-21
        **/
        CommentEntity commentEntity = new CommentEntity(); //새로운 엔티티 생성
        commentEntity.setRelatId(relatId); //board정보 입력
        commentEntity.setMemberEntity(memberRepository.findByMemberId(memberId));//member정보 입력
        commentEntity.setContent(content);//댓글 내용입력
        commentEntity.setDelYn("N");//삭제여부
        commentEntity.setPrivateYn(privateYn);
        commentEntity.setRelatDiv(relatDiv);
        commentRepository.save(commentEntity);
    }

    @Override
    public List<CommentListDTO> getCommentList(Long relatid,String relatDiv) {
        /**
         * 댓글 리스트 가져오기
         * 메서드명 : getCommentList
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-21
        **/
        List<CommentListDTO> commentList = null;
        List<CommentEntity> commentEntities= commentRepository.findByRelatIdAndRelatDivAndDelYnOrderByCreatedDateAsc(relatid,relatDiv,"N");
        commentList = commentEntities.stream().map(comment -> modelMapper.map(comment, CommentListDTO.class)).collect(Collectors.toList());//commetlist entity>dto
        for (int i = 0; i < commentList.size(); i++) {
            commentList.get(i).setReCommentEntityList(getReCommentList(commentList.get(i).getCommentId()));
        }
        return commentList;
    }

    @Override
    public void delComment(Long commentId) {
        /**
         * 댓글삭제
         * 메서드명 : delComment
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-21
        **/
        CommentEntity commentEntity = commentRepository.findByCommentId(commentId);
        commentEntity.setDelYn("Y");
        commentRepository.save(commentEntity);
    }

    @Override
    public List<ReCommentEntity> getReCommentList(Long relatId) {
        return reCommentRepository.findByRelatIdAndDelYnOrderByCreatedDate(relatId,"N");
    }

    @Override
    public void delReComment(Long commentId) {
        ReCommentEntity reCommentEntity = reCommentRepository.findByCommentId(commentId);
        reCommentEntity.setDelYn("Y");
        reCommentRepository.save(reCommentEntity);
    }

    @Override
    public void addReComment(MemberEntity memberEntity, Long relatId, String content, String privateYn, String targetNickName) {
        ReCommentEntity reCommentEntity = new ReCommentEntity();
        reCommentEntity.setMemberEntity(memberEntity);
        reCommentEntity.setRelatId(relatId);
        reCommentEntity.setContent(content);
        reCommentEntity.setDelYn("N");
        reCommentEntity.setPrivateYn(privateYn);
        reCommentEntity.setTargetNickname(targetNickName);
        reCommentRepository.save(reCommentEntity);
    }
}
