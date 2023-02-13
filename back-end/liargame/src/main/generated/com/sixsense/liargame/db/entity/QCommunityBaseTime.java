package com.sixsense.liargame.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommunityBaseTime is a Querydsl query type for CommunityBaseTime
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QCommunityBaseTime extends EntityPathBase<CommunityBaseTime> {

    private static final long serialVersionUID = 1884701619L;

    public static final QCommunityBaseTime communityBaseTime = new QCommunityBaseTime("communityBaseTime");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public QCommunityBaseTime(String variable) {
        super(CommunityBaseTime.class, forVariable(variable));
    }

    public QCommunityBaseTime(Path<? extends CommunityBaseTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommunityBaseTime(PathMetadata metadata) {
        super(CommunityBaseTime.class, metadata);
    }

}

