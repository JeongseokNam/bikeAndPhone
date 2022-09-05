package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBikeModelEntity is a Querydsl query type for BikeModelEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBikeModelEntity extends EntityPathBase<BikeModelEntity> {

    private static final long serialVersionUID = -2002602807L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBikeModelEntity bikeModelEntity = new QBikeModelEntity("bikeModelEntity");

    public final QBikeBrandEntity bikeBrandEntity;

    public final StringPath bikeModel = createString("bikeModel");

    public final NumberPath<Long> modelId = createNumber("modelId", Long.class);

    public QBikeModelEntity(String variable) {
        this(BikeModelEntity.class, forVariable(variable), INITS);
    }

    public QBikeModelEntity(Path<? extends BikeModelEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBikeModelEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBikeModelEntity(PathMetadata metadata, PathInits inits) {
        this(BikeModelEntity.class, metadata, inits);
    }

    public QBikeModelEntity(Class<? extends BikeModelEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bikeBrandEntity = inits.isInitialized("bikeBrandEntity") ? new QBikeBrandEntity(forProperty("bikeBrandEntity")) : null;
    }

}

