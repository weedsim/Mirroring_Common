package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.response.SubjectResp;
import com.sixsense.liargame.api.response.WordResp;
import com.sixsense.liargame.api.service.SubjectService;
import com.sixsense.liargame.db.entity.Subject;
import com.sixsense.liargame.db.entity.Word;
import com.sixsense.liargame.db.repository.SubjectRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

interface WordRepository extends JpaRepository<Word, Long> {

}

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubjectServiceImplTest {
    @Autowired
    SubjectService subjectService;
    @Autowired
    WordRepository wordRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @BeforeEach
    void beforeEach() {
        List<Subject> subjectList = new ArrayList<>();

        subjectList.add(Subject.builder().name("직업").build());
        subjectList.add(Subject.builder().name("식물").build());
        subjectList.add(Subject.builder().name("동물").build());
        subjectList.add(Subject.builder().name("운동").build());
        subjectList.add(Subject.builder().name("게임").build());
        subjectList.add(Subject.builder().name("음식").build());

        List<Word> wordList = new ArrayList<>();

        wordList.add(Word.builder().subjectId(1L).name("경찰").build());
        wordList.add(Word.builder().subjectId(1L).name("소방관").build());
        wordList.add(Word.builder().subjectId(1L).name("변호사").build());
        wordList.add(Word.builder().subjectId(1L).name("개발자").build());

        wordList.add(Word.builder().subjectId(2L).name("해바라기").build());
        wordList.add(Word.builder().subjectId(2L).name("진달래").build());
        wordList.add(Word.builder().subjectId(2L).name("고구마").build());
        wordList.add(Word.builder().subjectId(2L).name("감자").build());

        wordList.add(Word.builder().subjectId(3L).name("코끼리").build());
        wordList.add(Word.builder().subjectId(3L).name("사자").build());
        wordList.add(Word.builder().subjectId(3L).name("호랑이").build());
        wordList.add(Word.builder().subjectId(3L).name("토끼").build());

        wordList.add(Word.builder().subjectId(4L).name("야구").build());
        wordList.add(Word.builder().subjectId(4L).name("축구").build());
        wordList.add(Word.builder().subjectId(4L).name("탁구").build());
        wordList.add(Word.builder().subjectId(4L).name("농구").build());

        wordList.add(Word.builder().subjectId(5L).name("롤").build());
        wordList.add(Word.builder().subjectId(5L).name("에이펙스").build());
        wordList.add(Word.builder().subjectId(5L).name("배그").build());
        wordList.add(Word.builder().subjectId(5L).name("로아").build());

        wordList.add(Word.builder().subjectId(6L).name("떡볶이").build());
        wordList.add(Word.builder().subjectId(6L).name("닭갈비").build());
        wordList.add(Word.builder().subjectId(6L).name("초밥").build());
        wordList.add(Word.builder().subjectId(6L).name("돈까스").build());

        subjectRepository.saveAll(subjectList);
        wordRepository.saveAll(wordList);
    }

    @Test
    @Order(1)
    @DisplayName("1.주제들이 잘 나오는지")
    void selectAllSubjects() {
        List<SubjectResp> subjectResps = subjectService.selectAllSubjects();
        assertEquals(subjectResps.get(0).getName(), "직업");
        assertEquals(subjectResps.get(1).getName(), "식물");
        assertEquals(subjectResps.get(2).getName(), "동물");
        assertEquals(subjectResps.get(3).getName(), "운동");
        assertEquals(subjectResps.get(4).getName(), "게임");
        assertEquals(subjectResps.get(5).getName(), "음식");

        assertEquals(subjectResps.get(0).getId(), 1L);
        assertEquals(subjectResps.get(1).getId(), 2L);
        assertEquals(subjectResps.get(2).getId(), 3L);
        assertEquals(subjectResps.get(3).getId(), 4L);
        assertEquals(subjectResps.get(4).getId(), 5L);
        assertEquals(subjectResps.get(5).getId(), 6L);
    }

    @Test
    @Order(2)
    @DisplayName("2.제시어가 잘 나오는지")
    void selectRandomWord() {
        WordResp wordResp = subjectService.selectRandomWord(1L);
        assertTrue(wordResp.getName().equals("경찰") || wordResp.getName().equals("소방관") || wordResp.getName().equals("변호사") || wordResp.getName().equals("개발자"));
        wordResp = subjectService.selectRandomWord(1L);
        assertTrue(wordResp.getName().equals("경찰") || wordResp.getName().equals("소방관") || wordResp.getName().equals("변호사") || wordResp.getName().equals("개발자"));
        wordResp = subjectService.selectRandomWord(1L);
        assertTrue(wordResp.getName().equals("경찰") || wordResp.getName().equals("소방관") || wordResp.getName().equals("변호사") || wordResp.getName().equals("개발자"));
        wordResp = subjectService.selectRandomWord(1L);
        assertTrue(wordResp.getName().equals("경찰") || wordResp.getName().equals("소방관") || wordResp.getName().equals("변호사") || wordResp.getName().equals("개발자"));
        wordResp = subjectService.selectRandomWord(1L);
        assertTrue(wordResp.getName().equals("경찰") || wordResp.getName().equals("소방관") || wordResp.getName().equals("변호사") || wordResp.getName().equals("개발자"));
        wordResp = subjectService.selectRandomWord(1L);
        assertTrue(wordResp.getName().equals("경찰") || wordResp.getName().equals("소방관") || wordResp.getName().equals("변호사") || wordResp.getName().equals("개발자"));
        wordResp = subjectService.selectRandomWord(1L);
        assertTrue(wordResp.getName().equals("경찰") || wordResp.getName().equals("소방관") || wordResp.getName().equals("변호사") || wordResp.getName().equals("개발자"));
        wordResp = subjectService.selectRandomWord(1L);
        assertTrue(wordResp.getName().equals("경찰") || wordResp.getName().equals("소방관") || wordResp.getName().equals("변호사") || wordResp.getName().equals("개발자"));
        wordResp = subjectService.selectRandomWord(1L);
        assertTrue(wordResp.getName().equals("경찰") || wordResp.getName().equals("소방관") || wordResp.getName().equals("변호사") || wordResp.getName().equals("개발자"));
        wordResp = subjectService.selectRandomWord(1L);
        assertTrue(wordResp.getName().equals("경찰") || wordResp.getName().equals("소방관") || wordResp.getName().equals("변호사") || wordResp.getName().equals("개발자"));
        wordResp = subjectService.selectRandomWord(1L);
        assertTrue(wordResp.getName().equals("경찰") || wordResp.getName().equals("소방관") || wordResp.getName().equals("변호사") || wordResp.getName().equals("개발자"));

        wordResp = subjectService.selectRandomWord(2L);
        assertTrue(wordResp.getName().equals("해바라기") || wordResp.getName().equals("진달래") || wordResp.getName().equals("고구마") || wordResp.getName().equals("감자"));
        wordResp = subjectService.selectRandomWord(2L);
        assertTrue(wordResp.getName().equals("해바라기") || wordResp.getName().equals("진달래") || wordResp.getName().equals("고구마") || wordResp.getName().equals("감자"));
        wordResp = subjectService.selectRandomWord(2L);
        assertTrue(wordResp.getName().equals("해바라기") || wordResp.getName().equals("진달래") || wordResp.getName().equals("고구마") || wordResp.getName().equals("감자"));
        wordResp = subjectService.selectRandomWord(2L);
        assertTrue(wordResp.getName().equals("해바라기") || wordResp.getName().equals("진달래") || wordResp.getName().equals("고구마") || wordResp.getName().equals("감자"));
        wordResp = subjectService.selectRandomWord(2L);
        assertTrue(wordResp.getName().equals("해바라기") || wordResp.getName().equals("진달래") || wordResp.getName().equals("고구마") || wordResp.getName().equals("감자"));
        wordResp = subjectService.selectRandomWord(2L);
        assertTrue(wordResp.getName().equals("해바라기") || wordResp.getName().equals("진달래") || wordResp.getName().equals("고구마") || wordResp.getName().equals("감자"));
        wordResp = subjectService.selectRandomWord(2L);
        assertTrue(wordResp.getName().equals("해바라기") || wordResp.getName().equals("진달래") || wordResp.getName().equals("고구마") || wordResp.getName().equals("감자"));
        wordResp = subjectService.selectRandomWord(2L);
        assertTrue(wordResp.getName().equals("해바라기") || wordResp.getName().equals("진달래") || wordResp.getName().equals("고구마") || wordResp.getName().equals("감자"));
        wordResp = subjectService.selectRandomWord(2L);
        assertTrue(wordResp.getName().equals("해바라기") || wordResp.getName().equals("진달래") || wordResp.getName().equals("고구마") || wordResp.getName().equals("감자"));
        wordResp = subjectService.selectRandomWord(2L);
        assertTrue(wordResp.getName().equals("해바라기") || wordResp.getName().equals("진달래") || wordResp.getName().equals("고구마") || wordResp.getName().equals("감자"));

        wordResp = subjectService.selectRandomWord(3L);
        assertTrue(wordResp.getName().equals("코끼리") || wordResp.getName().equals("사자") || wordResp.getName().equals("호랑이") || wordResp.getName().equals("토끼"));
        wordResp = subjectService.selectRandomWord(3L);
        assertTrue(wordResp.getName().equals("코끼리") || wordResp.getName().equals("사자") || wordResp.getName().equals("호랑이") || wordResp.getName().equals("토끼"));
        wordResp = subjectService.selectRandomWord(3L);
        assertTrue(wordResp.getName().equals("코끼리") || wordResp.getName().equals("사자") || wordResp.getName().equals("호랑이") || wordResp.getName().equals("토끼"));
        wordResp = subjectService.selectRandomWord(3L);
        assertTrue(wordResp.getName().equals("코끼리") || wordResp.getName().equals("사자") || wordResp.getName().equals("호랑이") || wordResp.getName().equals("토끼"));
        wordResp = subjectService.selectRandomWord(3L);
        assertTrue(wordResp.getName().equals("코끼리") || wordResp.getName().equals("사자") || wordResp.getName().equals("호랑이") || wordResp.getName().equals("토끼"));
        wordResp = subjectService.selectRandomWord(3L);
        assertTrue(wordResp.getName().equals("코끼리") || wordResp.getName().equals("사자") || wordResp.getName().equals("호랑이") || wordResp.getName().equals("토끼"));
        wordResp = subjectService.selectRandomWord(3L);
        assertTrue(wordResp.getName().equals("코끼리") || wordResp.getName().equals("사자") || wordResp.getName().equals("호랑이") || wordResp.getName().equals("토끼"));
        wordResp = subjectService.selectRandomWord(3L);
        assertTrue(wordResp.getName().equals("코끼리") || wordResp.getName().equals("사자") || wordResp.getName().equals("호랑이") || wordResp.getName().equals("토끼"));
        wordResp = subjectService.selectRandomWord(3L);
        assertTrue(wordResp.getName().equals("코끼리") || wordResp.getName().equals("사자") || wordResp.getName().equals("호랑이") || wordResp.getName().equals("토끼"));
        wordResp = subjectService.selectRandomWord(3L);
        assertTrue(wordResp.getName().equals("코끼리") || wordResp.getName().equals("사자") || wordResp.getName().equals("호랑이") || wordResp.getName().equals("토끼"));

        wordResp = subjectService.selectRandomWord(4L);
        assertTrue(wordResp.getName().equals("야구") || wordResp.getName().equals("축구") || wordResp.getName().equals("탁구") || wordResp.getName().equals("농구"));
        wordResp = subjectService.selectRandomWord(4L);
        assertTrue(wordResp.getName().equals("야구") || wordResp.getName().equals("축구") || wordResp.getName().equals("탁구") || wordResp.getName().equals("농구"));
        wordResp = subjectService.selectRandomWord(4L);
        assertTrue(wordResp.getName().equals("야구") || wordResp.getName().equals("축구") || wordResp.getName().equals("탁구") || wordResp.getName().equals("농구"));
        wordResp = subjectService.selectRandomWord(4L);
        assertTrue(wordResp.getName().equals("야구") || wordResp.getName().equals("축구") || wordResp.getName().equals("탁구") || wordResp.getName().equals("농구"));
        wordResp = subjectService.selectRandomWord(4L);
        assertTrue(wordResp.getName().equals("야구") || wordResp.getName().equals("축구") || wordResp.getName().equals("탁구") || wordResp.getName().equals("농구"));
        wordResp = subjectService.selectRandomWord(4L);
        assertTrue(wordResp.getName().equals("야구") || wordResp.getName().equals("축구") || wordResp.getName().equals("탁구") || wordResp.getName().equals("농구"));
        wordResp = subjectService.selectRandomWord(4L);
        assertTrue(wordResp.getName().equals("야구") || wordResp.getName().equals("축구") || wordResp.getName().equals("탁구") || wordResp.getName().equals("농구"));
        wordResp = subjectService.selectRandomWord(4L);
        assertTrue(wordResp.getName().equals("야구") || wordResp.getName().equals("축구") || wordResp.getName().equals("탁구") || wordResp.getName().equals("농구"));
        wordResp = subjectService.selectRandomWord(4L);
        assertTrue(wordResp.getName().equals("야구") || wordResp.getName().equals("축구") || wordResp.getName().equals("탁구") || wordResp.getName().equals("농구"));
        wordResp = subjectService.selectRandomWord(4L);
        assertTrue(wordResp.getName().equals("야구") || wordResp.getName().equals("축구") || wordResp.getName().equals("탁구") || wordResp.getName().equals("농구"));


        wordResp = subjectService.selectRandomWord(5L);
        assertTrue(wordResp.getName().equals("롤") || wordResp.getName().equals("에이펙스") || wordResp.getName().equals("배그") || wordResp.getName().equals("로아"));
        wordResp = subjectService.selectRandomWord(5L);
        assertTrue(wordResp.getName().equals("롤") || wordResp.getName().equals("에이펙스") || wordResp.getName().equals("배그") || wordResp.getName().equals("로아"));
        wordResp = subjectService.selectRandomWord(5L);
        assertTrue(wordResp.getName().equals("롤") || wordResp.getName().equals("에이펙스") || wordResp.getName().equals("배그") || wordResp.getName().equals("로아"));
        wordResp = subjectService.selectRandomWord(5L);
        assertTrue(wordResp.getName().equals("롤") || wordResp.getName().equals("에이펙스") || wordResp.getName().equals("배그") || wordResp.getName().equals("로아"));
        wordResp = subjectService.selectRandomWord(5L);
        assertTrue(wordResp.getName().equals("롤") || wordResp.getName().equals("에이펙스") || wordResp.getName().equals("배그") || wordResp.getName().equals("로아"));
        wordResp = subjectService.selectRandomWord(5L);
        assertTrue(wordResp.getName().equals("롤") || wordResp.getName().equals("에이펙스") || wordResp.getName().equals("배그") || wordResp.getName().equals("로아"));
        wordResp = subjectService.selectRandomWord(5L);
        assertTrue(wordResp.getName().equals("롤") || wordResp.getName().equals("에이펙스") || wordResp.getName().equals("배그") || wordResp.getName().equals("로아"));
        wordResp = subjectService.selectRandomWord(5L);
        assertTrue(wordResp.getName().equals("롤") || wordResp.getName().equals("에이펙스") || wordResp.getName().equals("배그") || wordResp.getName().equals("로아"));
        wordResp = subjectService.selectRandomWord(5L);
        assertTrue(wordResp.getName().equals("롤") || wordResp.getName().equals("에이펙스") || wordResp.getName().equals("배그") || wordResp.getName().equals("로아"));
        wordResp = subjectService.selectRandomWord(5L);
        assertTrue(wordResp.getName().equals("롤") || wordResp.getName().equals("에이펙스") || wordResp.getName().equals("배그") || wordResp.getName().equals("로아"));

        wordResp = subjectService.selectRandomWord(6L);
        assertTrue(wordResp.getName().equals("떡볶이") || wordResp.getName().equals("닭갈비") || wordResp.getName().equals("초밥") || wordResp.getName().equals("돈까스"));

        wordResp = subjectService.selectRandomWord(6L);
        assertTrue(wordResp.getName().equals("떡볶이") || wordResp.getName().equals("닭갈비") || wordResp.getName().equals("초밥") || wordResp.getName().equals("돈까스"));
        wordResp = subjectService.selectRandomWord(6L);
        assertTrue(wordResp.getName().equals("떡볶이") || wordResp.getName().equals("닭갈비") || wordResp.getName().equals("초밥") || wordResp.getName().equals("돈까스"));
        wordResp = subjectService.selectRandomWord(6L);
        assertTrue(wordResp.getName().equals("떡볶이") || wordResp.getName().equals("닭갈비") || wordResp.getName().equals("초밥") || wordResp.getName().equals("돈까스"));
        wordResp = subjectService.selectRandomWord(6L);
        assertTrue(wordResp.getName().equals("떡볶이") || wordResp.getName().equals("닭갈비") || wordResp.getName().equals("초밥") || wordResp.getName().equals("돈까스"));
        wordResp = subjectService.selectRandomWord(6L);
        assertTrue(wordResp.getName().equals("떡볶이") || wordResp.getName().equals("닭갈비") || wordResp.getName().equals("초밥") || wordResp.getName().equals("돈까스"));
        wordResp = subjectService.selectRandomWord(6L);
        assertTrue(wordResp.getName().equals("떡볶이") || wordResp.getName().equals("닭갈비") || wordResp.getName().equals("초밥") || wordResp.getName().equals("돈까스"));
        wordResp = subjectService.selectRandomWord(6L);
        assertTrue(wordResp.getName().equals("떡볶이") || wordResp.getName().equals("닭갈비") || wordResp.getName().equals("초밥") || wordResp.getName().equals("돈까스"));
        wordResp = subjectService.selectRandomWord(6L);
        assertTrue(wordResp.getName().equals("떡볶이") || wordResp.getName().equals("닭갈비") || wordResp.getName().equals("초밥") || wordResp.getName().equals("돈까스"));
        wordResp = subjectService.selectRandomWord(6L);
        assertTrue(wordResp.getName().equals("떡볶이") || wordResp.getName().equals("닭갈비") || wordResp.getName().equals("초밥") || wordResp.getName().equals("돈까스"));
    }
}