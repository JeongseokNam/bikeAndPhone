package com.kong.bike.board.repository;


import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.BikeBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<BikeBoardEntity> searchBoardList(SearchDTO searchDTO, Pageable pageable);

    Page<BikeBoardEntity> searchManagerBoardList(SearchDTO searchDTO, Pageable pageable);
}
