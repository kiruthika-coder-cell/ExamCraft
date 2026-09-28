package com.sece.examcraft.controllers;

import com.sece.examcraft.entities.Question;
import com.sece.examcraft.entities.TestPaper;
import com.sece.examcraft.services.TestPaperService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test-papers")
public class TestPaperController {

    private final TestPaperService testPaperService;

    public TestPaperController(TestPaperService testPaperService) {
        this.testPaperService = testPaperService;
    }


    // Create Test Paper
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestPaper addTestPaper(
            @Valid @RequestBody TestPaper testPaper) {

        return testPaperService.addTestPaper(testPaper);
    }


    // Get all Test Papers
    @GetMapping
    public List<TestPaper> getAllTestPapers() {

        return testPaperService.getAllTestPapers();
    }


    // Get Test Paper by ID
    @GetMapping("/{id}")
    public TestPaper getTestPaperById(
            @PathVariable Long id) {

        return testPaperService.getTestPaperById(id);
    }
    @GetMapping("/usage-frequency")
    public List<Map<String, Object>> getQuestionUsageFrequency() {

        return testPaperService.getQuestionUsageFrequency();
    }


    // Get saved questions of Test Paper
    @GetMapping("/{id}/questions")
    public List<Question> getQuestionsForTest(
            @PathVariable Long id) {

        TestPaper testPaper =
                testPaperService.getTestPaperById(id);

        if (testPaper == null) {
            return List.of();
        }

        return testPaper.getQuestions();
    }
    @GetMapping("/count")
    public long getTestPaperCount() {
        return testPaperService.getTestPaperCount();
    }


    // Update Test Paper
    @PutMapping("/{id}")
    public TestPaper updateTestPaper(
            @PathVariable Long id,
            @Valid @RequestBody TestPaper testPaper) {

        return testPaperService.updateTestPaper(
                id,
                testPaper);
    }


    // Delete Test Paper
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTestPaper(
            @PathVariable Long id) {

        testPaperService.deleteTestPaper(id);
    }
}