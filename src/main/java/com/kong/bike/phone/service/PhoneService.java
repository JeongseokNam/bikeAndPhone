package com.kong.bike.phone.service;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.PhoneBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhoneService {
    void save(PhoneBoardEntity phoneBoardEntity, MemberEntity memberEntity);

    int getRecentPhoneId();

    Page<PhoneBoardEntity> searchPhoneList(SearchDTO searchDTO, Pageable pageable);

    Page<PhoneBoardEntity> findByDelYn(Pageable pageable);

    PhoneBoardEntity getPhoneInfo(Long phoneId);

    void viewCount(Long phoneId);

    void updatedPhone(PhoneBoardEntity phoneBoardEntity);

    void PhoneDelete(Long phoneId);

    void soldOut(Long phoneId);

    void soldOutCancel(Long phoneId);

    List<PhoneBoardEntity> getMyPhoneList(Long memberId);


    Page<PhoneBoardEntity> findMyPhoneBoardList(String delYn,MemberEntity memberEntity, Pageable pageable);

    void deletePhone(Long phoneId);

}
