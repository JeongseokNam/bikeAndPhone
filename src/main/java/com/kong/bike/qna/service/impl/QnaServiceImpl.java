package com.kong.bike.qna.service.impl;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.QnaCountEntity;
import com.kong.bike.entity.QnaEntity;
import com.kong.bike.qna.repository.QnaCountRepository;
import com.kong.bike.qna.repository.QnaRepository;
import com.kong.bike.qna.repository.QnaRepositoryCustom;
import com.kong.bike.qna.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QnaServiceImpl implements QnaService {

    @Autowired
    QnaRepository qnaRepository;
    @Autowired
    QnaRepositoryCustom qnaRepositoryCustom;

    @Autowired
    QnaCountRepository qnaCountRepository;
    @Override
    public void addQuestion(String qnaTitle, String questionContent, MemberEntity memberEntity) {
        QnaEntity qnaEntity = new QnaEntity();
        qnaEntity.setQnaTitle(qnaTitle);
        qnaEntity.setQuestionContent(questionContent);
        qnaEntity.setQuestionMemberEntity(memberEntity);
        qnaEntity.setQuestionTime(LocalDateTime.now());
        qnaEntity.setDelYn("N");
        qnaRepository.save(qnaEntity);
        QnaCountEntity qnaCountEntity = qnaCountRepository.findByCountName("newQna");
        if (qnaCountEntity!=null){
            qnaCountEntity.setCount(qnaCountEntity.getCount()+1);
            qnaCountRepository.save(qnaCountEntity);
        }else {
            qnaCountEntity = new QnaCountEntity();
            qnaCountEntity.setCountId(0L);
            qnaCountEntity.setCount(1);
            qnaCountEntity.setCountName("newQna");
            qnaCountRepository.save(qnaCountEntity);
        }
    }

    @Override
    public Page<QnaEntity> findByQuestionMemberEntity(MemberEntity memberEntity, Pageable pageable) {
        return qnaRepository.findByQuestionMemberEntityAndDelYn(memberEntity,"N",pageable);
    }

    @Override
    public void deleteQna(Long qnaId) {
        QnaEntity qnaEntity = qnaRepository.findByQnaId(qnaId);
        qnaEntity.setDelYn("Y");
        qnaRepository.save(qnaEntity);
    }

    @Override
    public Page<QnaEntity> findAll(Pageable pageable) {
        return qnaRepository.findAll(pageable);
    }

    @Override
    public void addAnswer(Long qnaId, String answerContent, MemberEntity answerMember) {
        QnaEntity qnaEntity = qnaRepository.findByQnaId(qnaId);
        qnaEntity.setAnswerContent(answerContent);
        qnaEntity.setAnswerMemberEntity(answerMember);
        qnaEntity.setAnswerTime(LocalDateTime.now());
        qnaEntity.setAnswerYn("Y");
        qnaRepository.save(qnaEntity);
    }

    @Override
    public Page<QnaEntity> findByDelYn(Pageable pageable) {
        return qnaRepository.findByDelYn("N",pageable);
    }

    @Override
    public Page<QnaEntity> searchQnaList(SearchDTO searchDTO, Pageable pageable) {
        return qnaRepositoryCustom.searchQnaList(searchDTO,pageable);
    }

    @Override
    public QnaCountEntity getCountByCountName(String newQna) {
        return qnaCountRepository.findByCountName(newQna);
    }

    @Override
    public void resetCountByCountName(String newQna) {
        QnaCountEntity qnaCountEntity = qnaCountRepository.findByCountName(newQna);
        if (qnaCountEntity!=null) {
            qnaCountEntity.setCount(0);
            qnaCountRepository.save(qnaCountEntity);
        }
    }
}
