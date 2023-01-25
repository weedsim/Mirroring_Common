package com.sixsense.liargame.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNormalPlay is a Querydsl query type for NormalPlay
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNormalPlay extends EntityPathBase<NormalPlay> {

    private static final long serialVersionUID = -542112049L;

    public static final QNormalPlay normalPlay = new QNormalPlay("normalPlay");

    public final QPlayPK _super = new QPlayPK(this);

    //inherited
    public final NumberPath<Long> historyId = _super.historyId;

    public final StringPath role = createString("role");

    //inherited
    public final NumberPath<Long> userId = _super.userId;

    public QNormalPlay(String variable) {
        super(NormalPlay.class, forVariable(variable));
    }

    public QNormalPlay(Path<? extends NormalPlay> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNormalPlay(PathMetadata metadata) {
        super(NormalPlay.class, metadata);
    }

}

