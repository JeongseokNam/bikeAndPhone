package com.kong.bike.businessMember.service;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BusinessMemberService {
    Page<MemberEntity> searchBusinessMemberList(SearchDTO searchDTO, Pageable pageable);

    Page<MemberEntity> businessMemberList(Pageable pageable);

    void changeApproval(Long memberId, String approvalYn);
}
