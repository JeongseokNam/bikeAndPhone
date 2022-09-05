package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQnaEntity is a Querydsl query type for QnaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQnaEntity extends EntityPathBase<QnaEntity> {

    private static final long serialVersionUID = -634990491L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQnaEntity qnaEntity = new QQnaEntity("qnaEntity");

    public final StringPath answerContent = createString("answerContent");

    public final QMemberEntity answerMemberEntity;

    public final DateTimePath<java.time.LocalDateTime> answerTime = createDateTime("answerTime", java.time.LocalDateTime.class);

    public final StringPath answerYn = createString("answerYn");

    public final StringPath delYn = createString("delYn");

    public final NumberPath<Long> qnaId = createNumber("qnaId", Long.class);

    public final StringPath qnaTitle = createString("qnaTitle");

    public final StringPath questionContent = createString("questionContent");

    public final QMemberEntity questionMemberEntity;

    public final DateTimePath<java.time.LocalDateTime> questionTime = createDateTime("questionTime", java.time.LocalDateTime.class);

    public QQnaEntity(String variable) {
        this(QnaEntity.class, forVariable(variable), INITS);
    }

    public QQnaEntity(Path<? extends QnaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQnaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQnaEntity(PathMetadata metadata, PathInits inits) {
        this(QnaEntity.class, metadata, inits);
    }

    public QQnaEntity(Class<? extends QnaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.answerMemberEntity = inits.isInitialized("answerMemberEntity") ? new QMemberEntity(forProperty("answerMemberEntity")) : null;
        this.questionMemberEntity = inits.isInitialized("questionMemberEntity") ? new QMemberEntity(forProperty("questionMemberEntity")) : null;
    }

}

