package com.kong.bike.member.repository;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {

    Page<MemberEntity> searchMemberList(SearchDTO searchDTO, Pageable pageable);

}
