package com.kong.bike.businessMember.repository.Impl;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.businessMember.repository.BusinessMemberRepositoryCustom;
import com.kong.bike.entity.MemberEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kong.bike.entity.QMemberEntity.memberEntity;

@Slf4j
@RequiredArgsConstructor
@Repository
public class BusinessMemberRepositoryCustomImpl implements BusinessMemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    @Override
    public Page<MemberEntity> searchBusinessMemberList(SearchDTO searchDTO, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        String searchType = searchDTO.getSearchType();
        String keyword = searchDTO.getKeyword();
        String soleOrCorporate = searchDTO.getSoleOrCorporate();
        builder.and(memberEntity.delYn.eq("N"));
        builder.and(memberEntity.memberRole.eq("ROLE_BUSINESS"));
        if (!StringUtils.isNullOrEmpty(searchType)){
            if (searchType.equals("상호명")){
                builder.and(memberEntity.businessName.contains(keyword));
            } else if (searchType.equals("대표자명")){
                builder.and(memberEntity.ceoName.contains(keyword));
            } else if (searchType.equals("담장자명")){
                builder.and(memberEntity.realName.contains(keyword));
            }else if (searchType.equals("사업자등록번호")){
                builder.and(memberEntity.businessNum.contains(keyword));
            }
        }
        if (!StringUtils.isNullOrEmpty(soleOrCorporate)){
            builder.and(memberEntity.soleOrCorporate.eq(soleOrCorporate));
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
