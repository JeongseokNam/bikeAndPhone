package com.kong.bike.member.repository.Impl;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.member.repository.MemberRepositoryCustom;
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
import static com.kong.bike.entity.QQnaEntity.qnaEntity;

@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MemberEntity> searchMemberList(SearchDTO searchDTO, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        String searchType = searchDTO.getSearchType();
        String keyword = searchDTO.getKeyword();

        builder.and(memberEntity.delYn.eq("N"));
        if (!StringUtils.isNullOrEmpty(searchType)) {
            if (searchType.equals("아이디")) {
                if (!StringUtils.isNullOrEmpty(keyword))
                    builder.and(memberEntity.username.contains(keyword));
            } else if (searchType.equals("닉네임")) {
                if (!StringUtils.isNullOrEmpty(keyword))
                    builder.and(memberEntity.nickName.contains(keyword));
            } else if (searchType.equals("핸드폰번호")) {
                if (!StringUtils.isNullOrEmpty(keyword))
                    builder.and(memberEntity.phone.contains(keyword));
            }
        }
        List<MemberEntity> memberEntities = queryFactory
                .selectFrom(memberEntity)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(memberEntity.createDate.desc())
                .fetch();
        Long count = queryFactory
                .select(memberEntity.count())
                .from(memberEntity)
                .where(builder)
                .fetchOne();
        return new PageImpl<>(memberEntities, pageable, count);
    }
}
