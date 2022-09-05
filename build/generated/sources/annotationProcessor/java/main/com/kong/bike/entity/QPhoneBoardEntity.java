package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhoneBoardEntity is a Querydsl query type for PhoneBoardEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhoneBoardEntity extends EntityPathBase<PhoneBoardEntity> {

    private static final long serialVersionUID = -1118763811L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhoneBoardEntity phoneBoardEntity = new QPhoneBoardEntity("phoneBoardEntity");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final StringPath dealWay = createString("dealWay");

    public final StringPath delYn = createString("delYn");

    public final StringPath inquiry = createString("inquiry");

    public final StringPath location = createString("location");

    public final QMemberEntity memberEntity;

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> phoneId = createNumber("phoneId", Long.class);

    public final StringPath phoneYn = createString("phoneYn");

    public final StringPath price = createString("price");

    public final StringPath sellYn = createString("sellYn");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QPhoneBoardEntity(String variable) {
        this(PhoneBoardEntity.class, forVariable(variable), INITS);
    }

    public QPhoneBoardEntity(Path<? extends PhoneBoardEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhoneBoardEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhoneBoardEntity(PathMetadata metadata, PathInits inits) {
        this(PhoneBoardEntity.class, metadata, inits);
    }

    public QPhoneBoardEntity(Class<? extends PhoneBoardEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new QMemberEntity(forProperty("memberEntity")) : null;
    }

}

