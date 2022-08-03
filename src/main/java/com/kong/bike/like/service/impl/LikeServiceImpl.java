package com.kong.bike.like.service.impl;

import com.kong.bike.board.repository.BoardRepository;
import com.kong.bike.entity.LikeEntity;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.like.repository.LikeRepository;
import com.kong.bike.like.service.LikeService;
import com.kong.bike.member.repository.MemberRepository;
import com.kong.bike.phone.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepository likeRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PhoneRepository phoneRepository;

    @Override
    public void addLike(Long relatId, Long memberId, String relatDiv) {
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setRelatId(relatId);
        likeEntity.setRelatDiv(relatDiv);
        likeEntity.setMemberEntity(memberRepository.findByMemberId(memberId));
        likeRepository.save(likeEntity);
    }


    @Override
    public void delLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    @Override
    public List<LikeEntity> findByMemberEntity(MemberEntity memberEntity) {
        return likeRepository.findByMemberEntity(memberEntity);
    }

    @Override
    public LikeEntity findByMemberEntityAndRelatIdAndRelatDiv(MemberEntity tempMember, Long relatId, String relatDiv) {
        return likeRepository.findByMemberEntityAndRelatIdAndRelatDiv(tempMember,relatId,relatDiv);
    }
}

