package com.kong.bike.phone.service.impl;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.PhoneBoardEntity;
import com.kong.bike.phone.repository.PhoneRepository;
import com.kong.bike.phone.repository.PhoneRepositoryCustom;
import com.kong.bike.phone.service.PhoneService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    PhoneRepositoryCustom phoneRepositoryCustom;

    @Override
    public void save(PhoneBoardEntity phoneBoardEntity, MemberEntity memberEntity) { // 핸드폰 글 작성
        phoneBoardEntity.setDelYn("N");
        phoneBoardEntity.setMemberEntity(memberEntity);
        phoneBoardEntity.setCreatedDate(LocalDateTime.now());
        phoneBoardEntity.setModifiedDate(LocalDateTime.now());
        phoneRepository.save(phoneBoardEntity);
    }

    @Override
    public int getRecentPhoneId() {
        return phoneRepository.getRecentPhoneId();
    }

    @Override
    public Page<PhoneBoardEntity> searchPhoneList(SearchDTO searchDTO, Pageable pageable) {
        return phoneRepositoryCustom.searchPhoneList(searchDTO, pageable);
    }

    @Override
    public Page<PhoneBoardEntity> findByDelYn(Pageable pageable) {
        return phoneRepository.findByDelYn("N", pageable);
    }

    @Override
    public PhoneBoardEntity getPhoneInfo(Long phoneId) {
        return phoneRepository.findByPhoneId(phoneId);
    }

    @Override
    public void viewCount(Long phoneId) {
        PhoneBoardEntity tempEntity = phoneRepository.findByPhoneId(phoneId);
        tempEntity.setViewCount(tempEntity.getViewCount()+1);
        phoneRepository.save(tempEntity);
    }

    @Override
    public void updatedPhone(PhoneBoardEntity phoneBoardEntity) {
        //수정대상 원본데이터조회
        PhoneBoardEntity origin = phoneRepository.findByPhoneId(phoneBoardEntity.getPhoneId());
        //수정내용 셋팅
        phoneBoardEntity.setDelYn("N");
        phoneBoardEntity.setMemberEntity(origin.getMemberEntity());
        phoneBoardEntity.setModifiedDate(LocalDateTime.now());
        //수정내용>수정대상에 밀어넣기
        BeanUtils.copyProperties(phoneBoardEntity, origin);
        phoneRepository.save(origin);
    }

    @Override
    public void PhoneDelete(Long phoneId) {
        PhoneBoardEntity tempPhone = phoneRepository.findByPhoneId(phoneId);
        tempPhone.setDelYn("Y");
        phoneRepository.save(tempPhone);
    }

    @Override
    public void soldOut(Long phoneId) {
        PhoneBoardEntity tempPhone = phoneRepository.findByPhoneId(phoneId);
        tempPhone.setSellYn("Y");
        phoneRepository.save(tempPhone);
    }

    @Override
    public void soldOutCancel(Long phoneId) {
        PhoneBoardEntity tempPhone = phoneRepository.findByPhoneId(phoneId);
        tempPhone.setSellYn("N");
        phoneRepository.save(tempPhone);
    }

    @Override
    public List<PhoneBoardEntity> getMyPhoneList(Long memberId) {
        List<PhoneBoardEntity> list = phoneRepository.findByDelYn("N");
        List<PhoneBoardEntity> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMemberEntity().getMemberId().equals(memberId)) {
                newList.add(list.get(i));
            }
        }
        return newList;
    }

    @Override
    public Page<PhoneBoardEntity> findMyPhoneBoardList(String delYn,MemberEntity memberEntity, Pageable pageable) {
        return phoneRepository.findByDelYnAndMemberEntity(delYn,memberEntity,pageable);
    }

    @Override
    public void deletePhone(Long phoneId) {
        PhoneBoardEntity tempPhone = phoneRepository.findByPhoneId(phoneId);
        tempPhone.setDelYn("Y");
        phoneRepository.save(tempPhone);
    }
}
