package com.kong.bike.notice.repository;

import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    @Query(value = "select notice_id from(select row_number() over(order by notice_id desc) as num,notice_id from notice_entity)t where num= 1",nativeQuery = true)
    Integer getRecentNoticeId();

    Page<NoticeEntity> findByDelYn(String n, Pageable pageable);

    NoticeEntity findByNoticeId(Long noticeId);
}

//회원용 공지사항 페이징 처리, 썸머노트 한글 안되는거 해결하기
