package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLocationDetailEntity is a Querydsl query type for LocationDetailEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocationDetailEntity extends EntityPathBase<LocationDetailEntity> {

    private static final long serialVersionUID = 67941291L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLocationDetailEntity locationDetailEntity = new QLocationDetailEntity("locationDetailEntity");

    public final NumberPath<Long> locationDetailId = createNumber("locationDetailId", Long.class);

    public final StringPath locationDetailName = createString("locationDetailName");

    public final QLocationEntity locationEntity;

    public QLocationDetailEntity(String variable) {
        this(LocationDetailEntity.class, forVariable(variable), INITS);
    }

    public QLocationDetailEntity(Path<? extends LocationDetailEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLocationDetailEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLocationDetailEntity(PathMetadata metadata, PathInits inits) {
        this(LocationDetailEntity.class, metadata, inits);
    }

    public QLocationDetailEntity(Class<? extends LocationDetailEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.locationEntity = inits.isInitialized("locationEntity") ? new QLocationEntity(forProperty("locationEntity")) : null;
    }

}

