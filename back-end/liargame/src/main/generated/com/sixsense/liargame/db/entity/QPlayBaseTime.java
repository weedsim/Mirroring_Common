package com.sixsense.liargame.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlayBaseTime is a Querydsl query type for PlayBaseTime
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QPlayBaseTime extends EntityPathBase<PlayBaseTime> {

    private static final long serialVersionUID = 536566182L;

    public static final QPlayBaseTime playBaseTime = new QPlayBaseTime("playBaseTime");

    public final DateTimePath<java.time.LocalDateTime> playedAt = createDateTime("playedAt", java.time.LocalDateTime.class);

    public QPlayBaseTime(String variable) {
        super(PlayBaseTime.class, forVariable(variable));
    }

    public QPlayBaseTime(Path<? extends PlayBaseTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlayBaseTime(PathMetadata metadata) {
        super(PlayBaseTime.class, metadata);
    }

}

