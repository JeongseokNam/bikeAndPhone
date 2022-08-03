package com.kong.bike.comment.repository;

import com.kong.bike.entity.CommentEntity;
import com.kong.bike.entity.ReCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReCommentRepository extends JpaRepository<ReCommentEntity,Long> {

    List<ReCommentEntity> findByRelatIdAndDelYnOrderByCreatedDate(Long relatId,String delYn);

    ReCommentEntity findByCommentId(Long commentId);
}
