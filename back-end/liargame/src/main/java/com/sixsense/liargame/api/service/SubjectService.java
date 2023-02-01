package com.sixsense.liargame.api.service;

import com.sixsense.liargame.api.response.SubjectResp;
import com.sixsense.liargame.api.response.WordResp;
import com.sixsense.liargame.db.entity.Subject;
import com.sixsense.liargame.db.entity.Word;

import java.util.List;

public interface SubjectService {
    List<SubjectResp> selectAllSubjects();

    void selectRandomWord(Long subjectId, Long roomId);

    default SubjectResp toSubjectDto(Subject subject) {
        return SubjectResp.builder()
                .id(subject.getId())
                .name(subject.getName())
                .build();
    }

    default WordResp toWordDto(Word word) {
        return WordResp.builder()
                .id(word.getId())
                .name(word.getName())
                .build();
    }
}
