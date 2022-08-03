package com.kong.bike.notice.service.impl;

import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.NoticeEntity;
import com.kong.bike.member.repository.MemberRepository;
import com.kong.bike.notice.repository.NoticeRepository;
import com.kong.bike.notice.service.NoticeService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    MemberRepository memberRepository;

    @Override
    public void save(NoticeEntity noticeEntity, MemberEntity memberEntity) {
        noticeEntity.setMemberEntity(memberEntity);
        noticeEntity.setDelYn("N");
        noticeEntity.setCreateDate(LocalDateTime.now());
        noticeRepository.save(noticeEntity);
    }

    @Override
    public int getRecentNoticeId() {
        Integer i = noticeRepository.getRecentNoticeId();
        if (i == null){
            return 0;
        }else {
            return i;
        }

    }

    @Override
    public Page<NoticeEntity> findByDelYn(Pageable pageable) {
        return noticeRepository.findByDelYn("N", pageable);
    }

    @Override
    public void modifyNotice(NoticeEntity noticeEntity) {
        NoticeEntity original = noticeRepository.findByNoticeId(noticeEntity.getNoticeId());
        noticeEntity.setCreateDate(original.getCreateDate());
        noticeEntity.setModifyDate(LocalDateTime.now());
        noticeEntity.setDelYn("N");
        noticeEntity.setMemberEntity(original.getMemberEntity());
        BeanUtils.copyProperties(noticeEntity, original);
        noticeRepository.save(original);
    }

    @Override
    public NoticeEntity findByNoticeId(Long noticeId) {
        return noticeRepository.findByNoticeId(noticeId);
    }

    @Override
    public void deleteNotice(Long noticeId) {
        NoticeEntity notice = noticeRepository.findByNoticeId(noticeId);
        notice.setDelYn("Y");
        noticeRepository.save(notice);
    }
}
