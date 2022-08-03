package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReCommentEntity is a Querydsl query type for ReCommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReCommentEntity extends EntityPathBase<ReCommentEntity> {

    private static final long serialVersionUID = 1035859629L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReCommentEntity reCommentEntity = new QReCommentEntity("reCommentEntity");

    public final QTimeEntity _super = new QTimeEntity(this);

    public final NumberPath<Long> commentId = createNumber("commentId", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath delYn = createString("delYn");

    //inherited
    public final StringPath formattedCreatedDate = _super.formattedCreatedDate;

    //inherited
    public final StringPath formattedModifiedDate = _super.formattedModifiedDate;

    public final QMemberEntity memberEntity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath privateYn = createString("privateYn");

    public final NumberPath<Long> relatId = createNumber("relatId", Long.class);

    public final StringPath targetNickname = createString("targetNickname");

    public QReCommentEntity(String variable) {
        this(ReCommentEntity.class, forVariable(variable), INITS);
    }

    public QReCommentEntity(Path<? extends ReCommentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReCommentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReCommentEntity(PathMetadata metadata, PathInits inits) {
        this(ReCommentEntity.class, metadata, inits);
    }

    public QReCommentEntity(Class<? extends ReCommentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new QMemberEntity(forProperty("memberEntity")) : null;
    }

}

