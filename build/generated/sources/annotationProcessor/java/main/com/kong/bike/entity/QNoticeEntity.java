package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNoticeEntity is a Querydsl query type for NoticeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNoticeEntity extends EntityPathBase<NoticeEntity> {

    private static final long serialVersionUID = 902416029L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNoticeEntity noticeEntity = new QNoticeEntity("noticeEntity");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final StringPath delYn = createString("delYn");

    public final QMemberEntity memberEntity;

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> noticeId = createNumber("noticeId", Long.class);

    public final StringPath title = createString("title");

    public QNoticeEntity(String variable) {
        this(NoticeEntity.class, forVariable(variable), INITS);
    }

    public QNoticeEntity(Path<? extends NoticeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNoticeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNoticeEntity(PathMetadata metadata, PathInits inits) {
        this(NoticeEntity.class, metadata, inits);
    }

    public QNoticeEntity(Class<? extends NoticeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new QMemberEntity(forProperty("memberEntity")) : null;
    }

}

