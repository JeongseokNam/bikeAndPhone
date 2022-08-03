package com.kong.bike.member.repository;

import com.kong.bike.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {


    MemberEntity findByNickName(String inputNick);

    MemberEntity findByUsername(String username);

    MemberEntity findByMemberId(Long memberId);

    @Query("select t from MemberEntity as t where t.password = :inputPw")
    MemberEntity getUserInfoByPassword(String inputPw);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update member_entity as t set t.del_yn = 'Y',t.del_dttm= :nowTime WHERE t.member_id = :memberId", nativeQuery = true)
    void updateDelMember(Long memberId, String nowTime);

    Page<MemberEntity> findByDelYn(String n, Pageable pageable);


    MemberEntity findByUsernameAndDelYn(String username,String delYn);

    MemberEntity findByPhoneAndDelYn(String phone, String n);

    @Query(value = "select member_id from(select row_number() over(order by member_id desc) as num, member_id from member_entity)t where num= 1",nativeQuery = true)
    int getRecentMemberId();


}
