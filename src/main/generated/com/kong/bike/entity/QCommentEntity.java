package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentEntity is a Querydsl query type for CommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentEntity extends EntityPathBase<CommentEntity> {

    private static final long serialVersionUID = 1109692832L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentEntity commentEntity = new QCommentEntity("commentEntity");

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

    public final StringPath relatDiv = createString("relatDiv");

    public final NumberPath<Long> relatId = createNumber("relatId", Long.class);

    public QCommentEntity(String variable) {
        this(CommentEntity.class, forVariable(variable), INITS);
    }

    public QCommentEntity(Path<? extends CommentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentEntity(PathMetadata metadata, PathInits inits) {
        this(CommentEntity.class, metadata, inits);
    }

    public QCommentEntity(Class<? extends CommentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new QMemberEntity(forProperty("memberEntity")) : null;
    }

}

