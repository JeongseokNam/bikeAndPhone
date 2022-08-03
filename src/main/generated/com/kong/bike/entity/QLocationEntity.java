package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocationEntity is a Querydsl query type for LocationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocationEntity extends EntityPathBase<LocationEntity> {

    private static final long serialVersionUID = 1102810426L;

    public static final QLocationEntity locationEntity = new QLocationEntity("locationEntity");

    public final NumberPath<Long> locationId = createNumber("locationId", Long.class);

    public final StringPath locationName = createString("locationName");

    public QLocationEntity(String variable) {
        super(LocationEntity.class, forVariable(variable));
    }

    public QLocationEntity(Path<? extends LocationEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocationEntity(PathMetadata metadata) {
        super(LocationEntity.class, metadata);
    }

}

