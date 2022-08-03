package com.kong.bike.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileEntity is a Querydsl query type for FileEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFileEntity extends EntityPathBase<FileEntity> {

    private static final long serialVersionUID = -748477759L;

    public static final QFileEntity fileEntity = new QFileEntity("fileEntity");

    public final QTimeEntity _super = new QTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath delYn = createString("delYn");

    public final StringPath fileDiv = createString("fileDiv");

    public final StringPath fileExt = createString("fileExt");

    public final NumberPath<Long> fileId = createNumber("fileId", Long.class);

    public final NumberPath<Integer> fileNum = createNumber("fileNum", Integer.class);

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    //inherited
    public final StringPath formattedCreatedDate = _super.formattedCreatedDate;

    //inherited
    public final StringPath formattedModifiedDate = _super.formattedModifiedDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath realFileNm = createString("realFileNm");

    public final NumberPath<Long> relatId = createNumber("relatId", Long.class);

    public final StringPath saveFileNm = createString("saveFileNm");

    public QFileEntity(String variable) {
        super(FileEntity.class, forVariable(variable));
    }

    public QFileEntity(Path<? extends FileEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileEntity(PathMetadata metadata) {
        super(FileEntity.class, metadata);
    }

}

