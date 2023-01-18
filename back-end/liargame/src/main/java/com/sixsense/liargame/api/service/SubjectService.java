package com.sixsense.liargame.api.service;

import com.sixsense.liargame.common.model.response.SubjectDto;
import com.sixsense.liargame.common.model.response.WordDto;
import com.sixsense.liargame.db.entity.Subject;
import com.sixsense.liargame.db.entity.Word;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> selectAllSubjects();

    WordDto selectRandomWord(Long subjectId);

    default SubjectDto toSubjectDto(Subject subject) {
        return SubjectDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .build();
    }

    default WordDto toWordDto(Word word) {
        return WordDto.builder()
                .id(word.getId())
                .name(word.getName())
                .build();
    }
}
