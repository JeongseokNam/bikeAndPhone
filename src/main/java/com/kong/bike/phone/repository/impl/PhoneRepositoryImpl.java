package com.kong.bike.phone.repository.impl;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.PhoneBoardEntity;
import com.kong.bike.phone.repository.PhoneRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.kong.bike.entity.QMemberEntity.memberEntity;
import static com.kong.bike.entity.QPhoneBoardEntity.phoneBoardEntity;

@Slf4j
@RequiredArgsConstructor
public class PhoneRepositoryImpl implements PhoneRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PhoneBoardEntity> searchPhoneList(SearchDTO searchDTO, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        String searchType = searchDTO.getSearchType();
        String keyword = searchDTO.getKeyword();

        builder.and(phoneBoardEntity.delYn.eq("N"));
        if (!StringUtils.isNullOrEmpty(searchType)){
            if (searchType.equals("제목")){
                if (!StringUtils.isNullOrEmpty(keyword))
                    builder.and(phoneBoardEntity.title.contains(keyword));
            } else if (searchType.equals("글쓴이")){
                if (!StringUtils.isNullOrEmpty(keyword))
                    builder.and(phoneBoardEntity.memberEntity.nickName.contains(keyword));
            }
        }
        if (!StringUtils.isNullOrEmpty(searchDTO.getLocation())){
            builder.and(phoneBoardEntity.location.eq(searchDTO.getLocation()));
        }
        List<PhoneBoardEntity> phoneEntities = queryFactory
                .selectFrom(phoneBoardEntity)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(phoneBoardEntity.createdDate.desc())
                .fetch();
        Long count = queryFactory
                .select(phoneBoardEntity.count())
                .from(phoneBoardEntity)
                .where(builder)
                .fetchOne();
        return new PageImpl<>(phoneEntities, pageable, count);
    }
}
