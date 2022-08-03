package com.kong.bike.qna.repository.Impl;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.QnaEntity;
import com.kong.bike.qna.repository.QnaRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.kong.bike.entity.QQnaEntity.qnaEntity;

@Slf4j
@RequiredArgsConstructor
public class QnaRepositoryImpl implements QnaRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<QnaEntity> searchQnaList(SearchDTO searchDTO, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        String searchType = searchDTO.getSearchType();
        String keyword = searchDTO.getKeyword();

        builder.and(qnaEntity.delYn.eq("N"));
        if (!StringUtils.isNullOrEmpty(searchType)) {
            if (searchType.equals("제목")) {
                if (!StringUtils.isNullOrEmpty(keyword))
                    builder.and(qnaEntity.qnaTitle.contains(keyword));
            } else if (searchType.equals("작성자")) {
                if (!StringUtils.isNullOrEmpty(keyword))
                    builder.and(qnaEntity.questionMemberEntity.nickName.contains(keyword));
            }
        }
        List<QnaEntity> qnaEntities = queryFactory
                .selectFrom(qnaEntity)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qnaEntity.questionTime.desc())
                .fetch();
        Long count = queryFactory
                .select(qnaEntity.count())
                .from(qnaEntity)
                .where(builder)
                .fetchOne();
        return new PageImpl<>(qnaEntities, pageable, count);
    }

}
