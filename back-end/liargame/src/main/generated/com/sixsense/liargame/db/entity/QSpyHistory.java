package com.sixsense.liargame.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSpyHistory is a Querydsl query type for SpyHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSpyHistory extends EntityPathBase<SpyHistory> {

    private static final long serialVersionUID = 2036279628L;

    public static final QSpyHistory spyHistory = new QSpyHistory("spyHistory");

    public final QHistory _super = new QHistory(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final NumberPath<Long> liar = _super.liar;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> playedAt = _super.playedAt;

    public final NumberPath<Long> spy = createNumber("spy", Long.class);

    //inherited
    public final StringPath winner = _super.winner;

    public QSpyHistory(String variable) {
        super(SpyHistory.class, forVariable(variable));
    }

    public QSpyHistory(Path<? extends SpyHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSpyHistory(PathMetadata metadata) {
        super(SpyHistory.class, metadata);
    }

}

