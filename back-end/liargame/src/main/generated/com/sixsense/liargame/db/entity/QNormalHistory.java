package com.sixsense.liargame.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNormalHistory is a Querydsl query type for NormalHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNormalHistory extends EntityPathBase<NormalHistory> {

    private static final long serialVersionUID = 437583641L;

    public static final QNormalHistory normalHistory = new QNormalHistory("normalHistory");

    public final QHistory _super = new QHistory(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final NumberPath<Long> liar = _super.liar;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> playedAt = _super.playedAt;

    //inherited
    public final StringPath winner = _super.winner;

    public QNormalHistory(String variable) {
        super(NormalHistory.class, forVariable(variable));
    }

    public QNormalHistory(Path<? extends NormalHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNormalHistory(PathMetadata metadata) {
        super(NormalHistory.class, metadata);
    }

}

