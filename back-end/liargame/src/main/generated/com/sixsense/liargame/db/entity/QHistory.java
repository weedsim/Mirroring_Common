package com.sixsense.liargame.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHistory is a Querydsl query type for History
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHistory extends EntityPathBase<History> {

    private static final long serialVersionUID = -1112037632L;

    public static final QHistory history = new QHistory("history");

    public final QPlayBaseTime _super = new QPlayBaseTime(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> liar = createNumber("liar", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> playedAt = _super.playedAt;

    public final StringPath winner = createString("winner");

    public QHistory(String variable) {
        super(History.class, forVariable(variable));
    }

    public QHistory(Path<? extends History> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHistory(PathMetadata metadata) {
        super(History.class, metadata);
    }

}

