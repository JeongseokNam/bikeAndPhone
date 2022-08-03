package com.kong.bike.member.service.impl;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.member.repository.MemberRepository;
import com.kong.bike.member.repository.MemberRepositoryCustom;
import com.kong.bike.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRepositoryCustom memberRepositoryCustom;

    @Override
    public MemberEntity getUserInfoByLoginId(String inputId) {
        /**
         * 메서드명 : getUserInfoByLoginId
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-09
         **/
        return memberRepository.findByUsername(inputId);
    }

    @Override
    public MemberEntity getUserInfoByNickName(String inputNick) {
        return memberRepository.findByNickName(inputNick);
    }

    @Override
    public void save(MemberEntity memberEntity) { //회원정보 저장
        memberEntity.setDelYn("N");
        memberEntity.setMemberRole("ROLE_USER");
        memberEntity.setCreateDate(LocalDateTime.now());
        memberRepository.save(memberEntity);
    }


    @Override
    public MemberEntity findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Override
    public MemberEntity findByMemberId(Long memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    @Override
    public void updateDelMember(Long memberId) {
        String nowTime = LocalDateTime.now().toString();
        memberRepository.updateDelMember(memberId, nowTime);
    }

//    @Transactional
//    @Override
//    public void nickNameUpdate(MemberEntity memberEntity) {
//        MemberEntity persistance = memberRepository.findByMemberId(memberEntity.getMemberId());
//
//        String rawPassword = memberEntity.getPassword();
//        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//        persistance.setPassword(encPassword);
//        persistance.setNickName(memberEntity.getNickName());
//    }


    @Override
    public void nickNameUpdate(Long memberId, String inputNick) {
        MemberEntity tempMember = memberRepository.findByMemberId(memberId);
        tempMember.setModifiedDate(LocalDateTime.now());
        tempMember.setNickName(inputNick);
        memberRepository.save(tempMember);
    }

    @Override
    public MemberEntity passwordUpdate(Long memberId, String password) {
        MemberEntity tempMember = memberRepository.findByMemberId(memberId);
        tempMember.setModifiedDate(LocalDateTime.now());
        tempMember.setPassword(password);
        return memberRepository.save(tempMember);
    }

    @Override
    public Page<MemberEntity> findByDelYn(Pageable pageable) {
        return memberRepository.findByDelYn("N",pageable);
    }

    @Override
    public MemberEntity findByPhone(String phone) {
        return memberRepository.findByPhoneAndDelYn(phone,"N");
    }

    @Override
    public void roleUpdate(Long memberId, String memberRole) {
        MemberEntity tempMember = memberRepository.findByMemberId(memberId);
        tempMember.setModifiedDate(LocalDateTime.now());
        tempMember.setMemberRole(memberRole);
        memberRepository.save(tempMember);
    }

    @Override
    public MemberEntity findByUsernameAndDelYn(String username) {
        return memberRepository.findByUsernameAndDelYn(username,"N");
    }

    @Override
    public void emailUpdate(Long memberId, String inputEmail) {
        MemberEntity tempMember = memberRepository.findByMemberId(memberId);
        tempMember.setModifiedDate(LocalDateTime.now());
        tempMember.setEmail(inputEmail);
        memberRepository.save(tempMember);
    }

    @Override
    public Page<MemberEntity> searchMemberList(SearchDTO searchDTO, Pageable pageable) {
        return memberRepositoryCustom.searchMemberList(searchDTO, pageable);
    }

    @Override
    public void saveBusinessMember(MemberEntity memberEntity) {
        memberEntity.setDelYn("N");
        memberEntity.setMemberRole("ROLE_BUSINESS");
        memberEntity.setApprovalYn("N");
        memberEntity.setCreateDate(LocalDateTime.now());
        memberRepository.save(memberEntity);
    }

    @Override
    public int getRecentMemberId() {
        return memberRepository.getRecentMemberId();
    }

    @Override
    public void updatePhone(MemberEntity tempMember, String phone) {
        tempMember.setPhone(phone);
        memberRepository.save(tempMember);
    }

    @Override
    public void accessRecord(Long memberId) {
        MemberEntity memberEntity = memberRepository.findByMemberId(memberId);
        if (memberEntity!=null){
            memberEntity.setRecentAccess(LocalDateTime.now());
        }
        memberRepository.save(memberEntity);
    }

}


