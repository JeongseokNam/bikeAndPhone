package com.kong.bike.comment.repository;

import com.kong.bike.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

    List<CommentEntity> findByRelatIdAndRelatDivAndDelYnOrderByCreatedDateAsc(Long relatId, String relatDiv, String delYn);
    CommentEntity findByCommentId(Long commentId);
}
