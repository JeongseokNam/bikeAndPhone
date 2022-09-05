package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBikeBrandEntity is a Querydsl query type for BikeBrandEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBikeBrandEntity extends EntityPathBase<BikeBrandEntity> {

    private static final long serialVersionUID = 1117177511L;

    public static final QBikeBrandEntity bikeBrandEntity = new QBikeBrandEntity("bikeBrandEntity");

    public final StringPath bikeBrand = createString("bikeBrand");

    public final NumberPath<Long> brandId = createNumber("brandId", Long.class);

    public QBikeBrandEntity(String variable) {
        super(BikeBrandEntity.class, forVariable(variable));
    }

    public QBikeBrandEntity(Path<? extends BikeBrandEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBikeBrandEntity(PathMetadata metadata) {
        super(BikeBrandEntity.class, metadata);
    }

}

