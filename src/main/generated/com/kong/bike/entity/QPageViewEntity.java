package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPageViewEntity is a Querydsl query type for PageViewEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPageViewEntity extends EntityPathBase<PageViewEntity> {

    private static final long serialVersionUID = 1561512985L;

    public static final QPageViewEntity pageViewEntity = new QPageViewEntity("pageViewEntity");

    public final StringPath beforeUrl = createString("beforeUrl");

    public final StringPath brwsrInfo = createString("brwsrInfo");

    public final StringPath ip = createString("ip");

    public final StringPath osInfo = createString("osInfo");

    public final StringPath pageUrl = createString("pageUrl");

    public final NumberPath<Long> pageViewId = createNumber("pageViewId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> regDttm = createDateTime("regDttm", java.time.LocalDateTime.class);

    public QPageViewEntity(String variable) {
        super(PageViewEntity.class, forVariable(variable));
    }

    public QPageViewEntity(Path<? extends PageViewEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPageViewEntity(PathMetadata metadata) {
        super(PageViewEntity.class, metadata);
    }

}

