package com.kong.bike.member.service;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    MemberEntity getUserInfoByLoginId(String inputId);

    MemberEntity getUserInfoByNickName(String inputNick);

    void save(MemberEntity memberEntity);

    MemberEntity findByUsername(String username);

    MemberEntity findByMemberId(Long memberId);

    void updateDelMember(Long memberId);

    // 마이페이지 닉네임 변경
    void nickNameUpdate(Long memberId, String inputNick);

    // 마이페이지 비밀번호 변경
    MemberEntity passwordUpdate(Long memberId, String password);

    Page<MemberEntity> findByDelYn(Pageable pageable);

    MemberEntity findByPhone(String phone);

    // 관리자페이지 권한변경
    void roleUpdate(Long memberId, String memberRole);

    MemberEntity findByUsernameAndDelYn(String username);

    void emailUpdate(Long memberId, String inputEmail);

    Page<MemberEntity> searchMemberList(SearchDTO searchDTO, Pageable pageable);

    void saveBusinessMember(MemberEntity memberEntity);

    int getRecentMemberId();

    void updatePhone(MemberEntity tempMember, String phone);

    void accessRecord(Long memberId);

}
