package com.kong.bike.notice.service;

import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {
    void save(NoticeEntity noticeEntity, MemberEntity memberEntity);

    int getRecentNoticeId();

    Page<NoticeEntity> findByDelYn(Pageable pageable);

    void modifyNotice(NoticeEntity noticeEntity);

    NoticeEntity findByNoticeId(Long noticeId);

    void deleteNotice(Long noticeId);
}
