package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberEntity is a Querydsl query type for MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberEntity extends EntityPathBase<MemberEntity> {

    private static final long serialVersionUID = 549150943L;

    public static final QMemberEntity memberEntity = new QMemberEntity("memberEntity");

    public final StringPath approvalYn = createString("approvalYn");

    public final StringPath businessCondition = createString("businessCondition");

    public final StringPath businessItem = createString("businessItem");

    public final StringPath businessLocation = createString("businessLocation");

    public final StringPath businessName = createString("businessName");

    public final StringPath businessNum = createString("businessNum");

    public final StringPath ceoName = createString("ceoName");

    public final StringPath corporateNum = createString("corporateNum");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final StringPath delDttm = createString("delDttm");

    public final StringPath delYn = createString("delYn");

    public final StringPath email = createString("email");

    public final StringPath emailConsent = createString("emailConsent");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath memberRole = createString("memberRole");

    public final StringPath messageConsent = createString("messageConsent");

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final StringPath nickName = createString("nickName");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath realName = createString("realName");

    public final DateTimePath<java.time.LocalDateTime> recentAccess = createDateTime("recentAccess", java.time.LocalDateTime.class);

    public final StringPath soleOrCorporate = createString("soleOrCorporate");

    public final StringPath username = createString("username");

    public QMemberEntity(String variable) {
        super(MemberEntity.class, forVariable(variable));
    }

    public QMemberEntity(Path<? extends MemberEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberEntity(PathMetadata metadata) {
        super(MemberEntity.class, metadata);
    }

}

