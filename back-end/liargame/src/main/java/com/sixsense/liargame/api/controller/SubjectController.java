package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.response.SubjectResp;
import com.sixsense.liargame.api.response.WordResp;
import com.sixsense.liargame.api.service.SubjectService;
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
    public ResponseEntity<List<SubjectResp>> getSubject() {
        return ResponseEntity.ok().body(subjectService.selectAllSubjects());
    }

    @GetMapping("/{subjectId}/words")
    public ResponseEntity<WordResp> getWord(@PathVariable Long subjectId) {
        return ResponseEntity.ok().body(subjectService.selectRandomWord(subjectId));
    }
}