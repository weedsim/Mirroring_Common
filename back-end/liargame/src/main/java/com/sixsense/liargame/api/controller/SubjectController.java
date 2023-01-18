package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.service.SubjectService;
import com.sixsense.liargame.common.model.response.SubjectDto;
import com.sixsense.liargame.common.model.response.WordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getSubject() {
        return ResponseEntity.ok().body(subjectService.selectAllSubjects());
    }

    @GetMapping("/{subjectId}/words")
    public ResponseEntity<WordDto> getWord(@PathVariable Long subjectId) {
        return ResponseEntity.ok().body(subjectService.selectRandomWord(subjectId));
    }
}
