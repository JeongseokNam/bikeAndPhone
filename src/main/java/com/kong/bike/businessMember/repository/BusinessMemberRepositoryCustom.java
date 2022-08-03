package com.kong.bike.businessMember.repository;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface BusinessMemberRepositoryCustom {
    Page<MemberEntity> searchBusinessMemberList(SearchDTO searchDTO, Pageable pageable);
}
