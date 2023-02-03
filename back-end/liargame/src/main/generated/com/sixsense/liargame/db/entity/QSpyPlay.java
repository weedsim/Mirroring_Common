package com.sixsense.liargame.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSpyPlay is a Querydsl query type for SpyPlay
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSpyPlay extends EntityPathBase<SpyPlay> {

    private static final long serialVersionUID = 265437564L;

    public static final QSpyPlay spyPlay = new QSpyPlay("spyPlay");

    public final QPlay _super = new QPlay(this);

    //inherited
    public final NumberPath<Long> historyId = _super.historyId;

    //inherited
    public final StringPath role = _super.role;

    //inherited
    public final NumberPath<Long> userId = _super.userId;

    public QSpyPlay(String variable) {
        super(SpyPlay.class, forVariable(variable));
    }

    public QSpyPlay(Path<? extends SpyPlay> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSpyPlay(PathMetadata metadata) {
        super(SpyPlay.class, metadata);
    }

}

