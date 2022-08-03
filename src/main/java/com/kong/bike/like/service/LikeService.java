package com.kong.bike.like.service;

import com.kong.bike.entity.LikeEntity;
import com.kong.bike.entity.MemberEntity;

import java.util.List;

public interface LikeService {

    void addLike(Long relatId, Long memberId,String relatDiv);

    void delLike(Long likeId);

    List<LikeEntity> findByMemberEntity(MemberEntity memberEntity);

    LikeEntity findByMemberEntityAndRelatIdAndRelatDiv(MemberEntity tempMember, Long relatId, String relatDiv);
}
