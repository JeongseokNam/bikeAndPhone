package com.kong.bike.businessMember.service.Impl;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.businessMember.repository.BusinessMemberRepository;
import com.kong.bike.businessMember.repository.BusinessMemberRepositoryCustom;
import com.kong.bike.businessMember.service.BusinessMemberService;
import com.kong.bike.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusineseeMemberServiceImpl implements BusinessMemberService {
    @Autowired
    private BusinessMemberRepository businessMemberRepository;
    @Autowired
    private BusinessMemberRepositoryCustom businessMemberRepositoryCustom;

    @Override
    public Page<MemberEntity> searchBusinessMemberList(SearchDTO searchDTO, Pageable pageable) {
        Page<MemberEntity> memberEntities = businessMemberRepositoryCustom.searchBusinessMemberList(searchDTO,pageable);
        return memberEntities;
    }

    @Override
    public Page<MemberEntity> businessMemberList(Pageable pageable) {
        return businessMemberRepository.findByDelYnAndMemberRoleOrderByCreateDateDesc("N","ROLE_BUSINESS",pageable);
    }

    @Override
    public void changeApproval(Long memberId, String approvalYn) {
        Optional<MemberEntity> memberEntity = businessMemberRepository.findById(memberId);
        MemberEntity tempMember = memberEntity.get();
        tempMember.setApprovalYn(approvalYn);
        businessMemberRepository.save(tempMember);
    }
}
