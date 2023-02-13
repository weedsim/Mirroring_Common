package com.sixsense.liargame.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGame is a Querydsl query type for Game
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QGame extends BeanPath<Game> {

    private static final long serialVersionUID = 154763526L;

    public static final QGame game = new QGame("game");

    public final NumberPath<Integer> curSpeaking = createNumber("curSpeaking", Integer.class);

    public final BooleanPath isTurned = createBoolean("isTurned");

    public final NumberPath<Long> liar = createNumber("liar", Long.class);

    public final ListPath<Long, NumberPath<Long>> participants = this.<Long, NumberPath<Long>>createList("participants", Long.class, NumberPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> startPerson = createNumber("startPerson", Integer.class);

    public final ListPath<com.sixsense.liargame.api.sse.Vote, SimplePath<com.sixsense.liargame.api.sse.Vote>> votes = this.<com.sixsense.liargame.api.sse.Vote, SimplePath<com.sixsense.liargame.api.sse.Vote>>createList("votes", com.sixsense.liargame.api.sse.Vote.class, SimplePath.class, PathInits.DIRECT2);

    public QGame(String variable) {
        super(Game.class, forVariable(variable));
    }

    public QGame(Path<? extends Game> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGame(PathMetadata metadata) {
        super(Game.class, metadata);
    }

}

