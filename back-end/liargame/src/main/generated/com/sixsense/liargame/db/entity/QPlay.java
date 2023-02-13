package com.sixsense.liargame.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlay is a Querydsl query type for Play
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlay extends EntityPathBase<Play> {

    private static final long serialVersionUID = 155041864L;

    public static final QPlay play = new QPlay("play");

    public final NumberPath<Long> historyId = createNumber("historyId", Long.class);

    public final StringPath role = createString("role");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QPlay(String variable) {
        super(Play.class, forVariable(variable));
    }

    public QPlay(Path<? extends Play> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlay(PathMetadata metadata) {
        super(Play.class, metadata);
    }

}

