package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.response.SubjectResp;
import com.sixsense.liargame.api.response.WordResp;
import com.sixsense.liargame.api.service.SubjectService;
import com.sixsense.liargame.db.entity.Subject;
import com.sixsense.liargame.db.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


    @Override
    public List<SubjectResp> selectAllSubjects() {
        return subjectRepository.findAll().stream().map(this::toSubjectDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public WordResp selectRandomWord(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        int size = subject.getWords().size();
        int idx = (int) (Math.random() * size);
        return toWordDto(subject.getWords().get(idx));
    }
}
