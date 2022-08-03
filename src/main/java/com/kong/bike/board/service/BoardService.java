package com.kong.bike.board.service;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    void save(BikeBoardEntity bikeBoardEntity, MemberEntity memberEntity);
    int getRecentBoardId();

    Page<BikeBoardEntity> boardList(Pageable pageable);

    Page<BikeBoardEntity> searchBoardList(SearchDTO searchDTO, Pageable pageable);

    BikeBoardEntity getBoardInfo(Long boardId);

    void modifiedBoard(BikeBoardEntity bikeBoardEntity);

    List<BikeBoardEntity> getMyBoardList(Long memberId);

    void soldOut(Long boardId);

    void soldOutCancel(Long boardId);

    void deleteBoard(Long boardId);

    void viewCount(Long boardId);

    List<BikeBoardEntity> findByDelYn(String n);

    BikeBoardEntity findByBoardId(Long boardId);

    List<BikeBrandEntity> getBrandList();

    List<BikeModelEntity> getModelListByBandName(String bikeBrand);

    List<LocationEntity> getLocationList();

    List<LocationDetailEntity> getLocationDetailByLocationName(String location);

    Page<BikeBoardEntity> searchManagerBoardList(SearchDTO searchDTO, Pageable pageable);

    Page<BikeBoardEntity> findByMyBoardList(String delYn, MemberEntity memberEntity, Pageable pageable);


}
