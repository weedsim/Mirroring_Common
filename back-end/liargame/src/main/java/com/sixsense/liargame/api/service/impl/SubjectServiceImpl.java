package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.response.SubjectResp;
import com.sixsense.liargame.api.response.WordResp;
import com.sixsense.liargame.api.service.SubjectService;
import com.sixsense.liargame.api.sse.GlobalRoom;
import com.sixsense.liargame.api.sse.NormalGame;
import com.sixsense.liargame.db.entity.Room;
import com.sixsense.liargame.db.entity.Subject;
import com.sixsense.liargame.db.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final Map<Integer, NormalGame> games;
    private final Map<Integer, Room> rooms;

    public SubjectServiceImpl(SubjectRepository subjectRepository, GlobalRoom globalRoom) {
        this.subjectRepository = subjectRepository;
        this.games = globalRoom.getGames();
        this.rooms = globalRoom.getRooms();
    }


    @Override
    public List<SubjectResp> selectAllSubjects() {
        return subjectRepository.findAll().stream().map(this::toSubjectDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void selectRandomWord(Long subjectId, Integer roomId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        int size = subject.getWords().size();
        int idx = (int) (Math.random() * size);
        WordResp wordResp = toWordDto(subject.getWords().get(idx));
        Room room = rooms.get(roomId);
        NormalGame normalGame = new NormalGame(room);
        normalGame.setWord(wordResp.getName());
        games.put(roomId, normalGame);
    }
}
