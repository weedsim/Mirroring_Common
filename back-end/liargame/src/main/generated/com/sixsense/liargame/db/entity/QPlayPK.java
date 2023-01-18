package com.sixsense.liargame.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlayPK is a Querydsl query type for PlayPK
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QPlayPK extends EntityPathBase<PlayPK> {

    private static final long serialVersionUID = -1328621501L;

    public static final QPlayPK playPK = new QPlayPK("playPK");

    public final NumberPath<Long> historyId = createNumber("historyId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QPlayPK(String variable) {
        super(PlayPK.class, forVariable(variable));
    }

    public QPlayPK(Path<? extends PlayPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlayPK(PathMetadata metadata) {
        super(PlayPK.class, metadata);
    }

}

