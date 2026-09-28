
package com.sece.examcraft.services;

import com.sece.examcraft.entities.Question;
import com.sece.examcraft.entities.TestPaper;
import com.sece.examcraft.repositories.TestPaperRepository;
import com.sece.examcraft.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestPaperService {

    private final TestPaperRepository testPaperRepository;
    private final QuestionService questionService;

    public TestPaperService(
            TestPaperRepository testPaperRepository,
            QuestionService questionService) {

        this.testPaperRepository = testPaperRepository;
        this.questionService = questionService;
    }

    // Add Test Paper and generate questions
    public TestPaper addTestPaper(TestPaper testPaper) {

        List<Question> questions =
                questionService.generateQuestions(
                        testPaper.getUnit(),
                        testPaper.getQuestionCount(),
                        testPaper.getEasyPercentage(),
                        testPaper.getMediumPercentage(),
                        testPaper.getHardPercentage()
                );

        testPaper.setQuestions(questions);

        return testPaperRepository.save(testPaper);
    }

    // Get all Test Papers
    public List<TestPaper> getAllTestPapers() {
        return testPaperRepository.findAll();
    }

    // Get Test Paper by ID
    public TestPaper getTestPaperById(Long id) {

        return testPaperRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Test paper not found with id: " + id
                        )
                );
    }

    // Update Test Paper
    public TestPaper updateTestPaper(
            Long id,
            TestPaper testPaper) {

        TestPaper existingTestPaper =
                testPaperRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Test paper not found with id: " + id
                                )
                        );

        existingTestPaper.setTestName(
                testPaper.getTestName());

        existingTestPaper.setSubject(
                testPaper.getSubject());

        existingTestPaper.setUnit(
                testPaper.getUnit());

        existingTestPaper.setQuestionCount(
                testPaper.getQuestionCount());

        existingTestPaper.setEasyPercentage(
                testPaper.getEasyPercentage());

        existingTestPaper.setMediumPercentage(
                testPaper.getMediumPercentage());

        existingTestPaper.setHardPercentage(
                testPaper.getHardPercentage());

        return testPaperRepository.save(existingTestPaper);
    }

    // Delete Test Paper
    public void deleteTestPaper(Long id) {
        testPaperRepository.deleteById(id);
    }

    // Question usage frequency
    public List<Map<String, Object>> getQuestionUsageFrequency() {

        List<Object[]> results =
                testPaperRepository.getQuestionUsageFrequency();

        List<Map<String, Object>> usageList =
                new ArrayList<>();

        for (Object[] row : results) {

            Map<String, Object> usage =
                    new HashMap<>();

            usage.put("questionId", row[0]);
            usage.put("usageCount", row[1]);

            usageList.add(usage);
        }

        return usageList;
    }

    public long getTestPaperCount() {
        return testPaperRepository.count();
    }
}