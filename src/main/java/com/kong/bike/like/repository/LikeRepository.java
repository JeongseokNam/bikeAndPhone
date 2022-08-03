package com.kong.bike.like.repository;

import com.kong.bike.entity.LikeEntity;
import com.kong.bike.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity,Long> {
    List<LikeEntity> findByMemberEntity(MemberEntity memberEntity);

    LikeEntity findByMemberEntityAndRelatIdAndRelatDiv(MemberEntity tempMember, Long relatId, String relatDiv);
}
