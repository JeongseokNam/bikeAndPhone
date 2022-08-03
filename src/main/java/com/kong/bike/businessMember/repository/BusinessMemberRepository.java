package com.kong.bike.businessMember.repository;

import com.kong.bike.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessMemberRepository extends JpaRepository<MemberEntity,Long> {
    Page<MemberEntity> findByDelYnAndMemberRoleOrderByCreateDateDesc(String delYn, String memberRole, Pageable pageable);
}
