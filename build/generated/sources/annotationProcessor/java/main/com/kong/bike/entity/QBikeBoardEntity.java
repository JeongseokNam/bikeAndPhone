package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBikeBoardEntity is a Querydsl query type for BikeBoardEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBikeBoardEntity extends EntityPathBase<BikeBoardEntity> {

    private static final long serialVersionUID = 88024774L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBikeBoardEntity bikeBoardEntity = new QBikeBoardEntity("bikeBoardEntity");

    public final StringPath accidentYn = createString("accidentYn");

    public final StringPath afterServiceYn = createString("afterServiceYn");

    public final StringPath bikeBrand = createString("bikeBrand");

    public final StringPath bikeCc = createString("bikeCc");

    public final StringPath bikeColor = createString("bikeColor");

    public final StringPath bikeGear = createString("bikeGear");

    public final StringPath bikeModel = createString("bikeModel");

    public final StringPath bikeYear = createString("bikeYear");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath changeAgree = createString("changeAgree");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final StringPath delYn = createString("delYn");

    public final StringPath documents = createString("documents");

    public final NumberPath<Integer> driveKm = createNumber("driveKm", Integer.class);

    public final StringPath location = createString("location");

    public final StringPath locationDetail = createString("locationDetail");

    public final QMemberEntity memberEntity;

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final StringPath negoAgree = createString("negoAgree");

    public final StringPath price = createString("price");

    public final StringPath sellYn = createString("sellYn");

    public final StringPath title = createString("title");

    public final StringPath tuningOptions1 = createString("tuningOptions1");

    public final StringPath tuningOptions2 = createString("tuningOptions2");

    public final StringPath tuningOptions3 = createString("tuningOptions3");

    public final StringPath tuningYn = createString("tuningYn");

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QBikeBoardEntity(String variable) {
        this(BikeBoardEntity.class, forVariable(variable), INITS);
    }

    public QBikeBoardEntity(Path<? extends BikeBoardEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBikeBoardEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBikeBoardEntity(PathMetadata metadata, PathInits inits) {
        this(BikeBoardEntity.class, metadata, inits);
    }

    public QBikeBoardEntity(Class<? extends BikeBoardEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new QMemberEntity(forProperty("memberEntity")) : null;
    }

}

