package com.kong.bike.board.service.impl;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.board.repository.*;
import com.kong.bike.board.service.BoardService;
import com.kong.bike.entity.*;
import com.kong.bike.file.repository.FileRepository;
import com.kong.bike.like.repository.LikeRepository;
import com.kong.bike.member.repository.MemberRepository;
import groovy.util.logging.Slf4j;
import org.modelmapper.ModelMapper;
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
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BoardRepositoryCustom boardRepositotyCustom;

    @Autowired
    BikeBrandRepository bikeBrandRepository;

    @Autowired
    BikeModelRepository bikeModelRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LocationDetailRepository locationDetailRepository;

    @Override
    public void save(BikeBoardEntity bikeBoardEntity, MemberEntity memberEntity) {
        /**
         * 글 저장시 회원 정보 저장
         * 메서드명 : save
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-13
         **/
        bikeBoardEntity.setDelYn("N");
        bikeBoardEntity.setMemberEntity(memberEntity);
        bikeBoardEntity.setCreatedDate(LocalDateTime.now());
        bikeBoardEntity.setModifiedDate(LocalDateTime.now());
        boardRepository.save(bikeBoardEntity);
    }

    public int getRecentBoardId() {
        Integer tempId = boardRepository.getRecentBoardId();
        if (tempId==null){
            return 0;
        }else {
            return tempId;
        }
    }

    @Override
    public Page<BikeBoardEntity> boardList(Pageable pageable) {
        /**
         * 게시글 리스트
         * 메서드명 : boardList
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-14
         **/
        return boardRepository.findAllByDelYnOrderByModifiedDateDesc("N", pageable);
    }

    @Override
    public Page<BikeBoardEntity> searchBoardList(SearchDTO searchDTO, Pageable pageable) {
        /**
         * 게시글 검색 대상 리스트
         * 메서드명 : SearchBoardList
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-15
         **/
        return boardRepositotyCustom.searchBoardList(searchDTO,pageable);
    }

    @Override
    public BikeBoardEntity getBoardInfo(Long boardId) {
        return boardRepository.findByBoardId(boardId);
    }

    @Override
    public void modifiedBoard(BikeBoardEntity bikeBoardEntity) {
        /**
         * 게시글수정
         * 메서드명 : modifiedBoard
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-16
         **/
        //수정대상 원본데이터조회
        BikeBoardEntity origin = boardRepository.findByBoardId(bikeBoardEntity.getBoardId());
        //수정내용 셋팅
        bikeBoardEntity.setDelYn("N");
        bikeBoardEntity.setMemberEntity(origin.getMemberEntity());
        bikeBoardEntity.setModifiedDate(LocalDateTime.now());
        //수정내용>수정대상에 밀어넣기
        BeanUtils.copyProperties(bikeBoardEntity, origin);
        boardRepository.save(origin);
    }

    @Override
    public List<BikeBoardEntity> getMyBoardList(Long memberId) {
        List<BikeBoardEntity> list = boardRepository.findByDelYn("N");
        List<BikeBoardEntity> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMemberEntity().getMemberId().equals(memberId)) {
                newList.add(list.get(i));
            }
        }
        return newList;
    }

    @Override
    public void soldOut(Long boardId) {
        BikeBoardEntity tempBoard = boardRepository.findByBoardId(boardId);
        tempBoard.setSellYn("Y");
        boardRepository.save(tempBoard);
    }

    @Override
    public void soldOutCancel(Long boardId) {
        BikeBoardEntity tempBoard = boardRepository.findByBoardId(boardId);
        tempBoard.setSellYn("N");
        boardRepository.save(tempBoard);
    }

    @Override
    public void deleteBoard(Long boardId) {
        BikeBoardEntity tempBoard = boardRepository.findByBoardId(boardId);
        tempBoard.setDelYn("Y");
        boardRepository.save(tempBoard);
    }

    @Override
    public void viewCount(Long boardId) {
        BikeBoardEntity tempEntity = boardRepository.findByBoardId(boardId);
        tempEntity.setViewCount(tempEntity.getViewCount()+1);
        boardRepository.save(tempEntity);
    }

    @Override
    public List<BikeBoardEntity> findByDelYn(String n) {
        return boardRepository.findByDelYn(n);
    }

    @Override
    public BikeBoardEntity findByBoardId(Long boardId) {
        return boardRepository.findByBoardId(boardId);
    }

    @Override
    public List<BikeBrandEntity> getBrandList() {
        return bikeBrandRepository.findAll();
    }

    @Override
    public List<BikeModelEntity> getModelListByBandName(String bikeBrand) {
        BikeBrandEntity bikeBrandEntity=bikeBrandRepository.findByBikeBrand(bikeBrand);
        return bikeModelRepository.findByBikeBrandEntity(bikeBrandEntity);
    }

    @Override
    public List<LocationEntity> getLocationList() {
        return locationRepository.findAll();
    }

    @Override
    public List<LocationDetailEntity> getLocationDetailByLocationName(String locationName) {
        LocationEntity locationEntity = locationRepository.findByLocationName(locationName);
        return locationDetailRepository.findByLocationEntity(locationEntity);
    }

    @Override
    public Page<BikeBoardEntity> searchManagerBoardList(SearchDTO searchDTO, Pageable pageable) {
        return boardRepositotyCustom.searchManagerBoardList(searchDTO,pageable);
    }

    @Override
    public Page<BikeBoardEntity> findByMyBoardList(String delYn, MemberEntity memberEntity, Pageable pageable) {
        return boardRepository.findByDelYnAndMemberEntity(delYn,memberEntity,pageable);
    }
}
