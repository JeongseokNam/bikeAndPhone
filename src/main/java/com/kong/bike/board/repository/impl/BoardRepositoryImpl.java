package com.kong.bike.board.repository.impl;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.board.repository.BoardRepositoryCustom;
import com.kong.bike.entity.BikeBoardEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.kong.bike.entity.QBikeBoardEntity.bikeBoardEntity;

@Slf4j
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BikeBoardEntity> searchBoardList(SearchDTO searchDTO, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();


        builder.and(bikeBoardEntity.delYn.eq("N"));

        if (!StringUtils.isNullOrEmpty(searchDTO.getBikeBrand())) {//제조회사검색
            builder.and(bikeBoardEntity.bikeBrand.eq(searchDTO.getBikeBrand()));
        }
        if (!StringUtils.isNullOrEmpty(searchDTO.getLocation())) { //지역
            builder.and(bikeBoardEntity.location.eq(searchDTO.getLocation()));
        }
        if (!StringUtils.isNullOrEmpty(searchDTO.getBikeYear())) { //년식
            builder.and(bikeBoardEntity.bikeYear.eq(searchDTO.getBikeYear()));
        }
        if (0 < searchDTO.getDriveKm()) { //주행거리
            builder.and(bikeBoardEntity.driveKm.between(searchDTO.getKmMin(), searchDTO.getKmMax()));
        }
        if (!StringUtils.isNullOrEmpty(searchDTO.getBikeModel())) { //모델
            builder.and(bikeBoardEntity.bikeModel.eq(searchDTO.getBikeModel()));
        }
        if (!StringUtils.isNullOrEmpty(searchDTO.getKeyword())) { //검색어
            builder.and(bikeBoardEntity.title.contains(searchDTO.getKeyword()));
        }
        List<BikeBoardEntity> boardEntities = queryFactory
                .selectFrom(bikeBoardEntity)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(bikeBoardEntity.modifiedDate.desc())
                .fetch();
        Long count = queryFactory
                .select(bikeBoardEntity.count())
                .from(bikeBoardEntity)
                .where(builder)
                .fetchOne();
        return new PageImpl<>(boardEntities, pageable, count);
    }

    @Override
    public Page<BikeBoardEntity> searchManagerBoardList(SearchDTO searchDTO, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        String searchType = searchDTO.getSearchType();
        String keyword = searchDTO.getKeyword();

        builder.and(bikeBoardEntity.delYn.eq("N"));
        if (!StringUtils.isNullOrEmpty(searchType)) {
            if (searchType.equals("제목")) {
                if (!StringUtils.isNullOrEmpty(keyword))
                    builder.and(bikeBoardEntity.title.contains(keyword));
            } else if (searchType.equals("작성자")) {
                if (!StringUtils.isNullOrEmpty(keyword))
                    builder.and(bikeBoardEntity.memberEntity.nickName.contains(keyword));
            }
        }
        List<BikeBoardEntity> boardEntities = queryFactory
                .selectFrom(bikeBoardEntity)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(bikeBoardEntity.modifiedDate.desc())
                .fetch();
        Long count = queryFactory
                .select(bikeBoardEntity.count())
                .from(bikeBoardEntity)
                .where(builder)
                .fetchOne();
        return new PageImpl<>(boardEntities, pageable, count);
    }
}



