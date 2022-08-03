package com.kong.bike.qna.repository;


import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.QnaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QnaRepositoryCustom {

    Page<QnaEntity> searchQnaList(SearchDTO searchDTO, Pageable pageable);
}
