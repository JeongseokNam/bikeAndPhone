package com.kong.bike.qna.service;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.QnaCountEntity;
import com.kong.bike.entity.QnaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QnaService {
    void addQuestion(String qnaTitle, String questionContent, MemberEntity memberEntity);

    Page<QnaEntity> findByQuestionMemberEntity(MemberEntity memberEntity, Pageable pageable);

    void deleteQna(Long qnaId);

    Page<QnaEntity> findAll(Pageable pageable);

    void addAnswer(Long qnaId, String answerContent, MemberEntity answerMember);

    Page<QnaEntity> findByDelYn(Pageable pageable);

    Page<QnaEntity> searchQnaList(SearchDTO searchDTO, Pageable pageable);

    QnaCountEntity getCountByCountName(String newQna);

    void resetCountByCountName(String newQna);
}
