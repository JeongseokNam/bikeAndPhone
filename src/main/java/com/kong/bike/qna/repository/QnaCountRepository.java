package com.kong.bike.qna.repository;

import com.kong.bike.entity.QnaCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaCountRepository extends JpaRepository<QnaCountEntity,Long> {
    QnaCountEntity findByCountName(String countName);
}
