package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QQnaCountEntity is a Querydsl query type for QnaCountEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQnaCountEntity extends EntityPathBase<QnaCountEntity> {

    private static final long serialVersionUID = 1034382992L;

    public static final QQnaCountEntity qnaCountEntity = new QQnaCountEntity("qnaCountEntity");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> countId = createNumber("countId", Long.class);

    public final StringPath countName = createString("countName");

    public QQnaCountEntity(String variable) {
        super(QnaCountEntity.class, forVariable(variable));
    }

    public QQnaCountEntity(Path<? extends QnaCountEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQnaCountEntity(PathMetadata metadata) {
        super(QnaCountEntity.class, metadata);
    }

}

