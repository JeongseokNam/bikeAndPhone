package com.kong.bike.phone.repository;

import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.PhoneBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneBoardEntity, Long> {

    @Query(value = "select phone_id from(select row_number() over(order by phone_id desc) as num, phone_id from phone_board_entity)t where num= 1",nativeQuery = true)
    int getRecentPhoneId();

    Page<PhoneBoardEntity> findByDelYn(String n, Pageable pageable);

    List<PhoneBoardEntity> findByDelYn(String n);

    PhoneBoardEntity findByPhoneId(Long phoneId);

    Page<PhoneBoardEntity> findByDelYnAndMemberEntity(String delYn,MemberEntity memberEntity,Pageable pageable);
}
