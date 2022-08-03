package com.kong.bike.phone.repository;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.PhoneBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhoneRepositoryCustom {

    Page<PhoneBoardEntity> searchPhoneList(SearchDTO searchDTO, Pageable pageable);
}
