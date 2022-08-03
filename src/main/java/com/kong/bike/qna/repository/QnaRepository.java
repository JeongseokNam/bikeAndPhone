package com.kong.bike.qna.repository;

import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.QnaCountEntity;
import com.kong.bike.entity.QnaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaRepository extends JpaRepository<QnaEntity,Long> {
    Page<QnaEntity> findByQuestionMemberEntityAndDelYn(MemberEntity memberEntity, String n, Pageable pageable);

    QnaEntity findByQnaId(Long qnaId);

    Page<QnaEntity> findByDelYn(String n, Pageable pageable);

}
