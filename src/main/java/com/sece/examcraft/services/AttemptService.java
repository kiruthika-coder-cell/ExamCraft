package com.sece.examcraft.services;

import com.sece.examcraft.entities.Attempt;
import com.sece.examcraft.entities.Question;
import com.sece.examcraft.entities.TestPaper;
import com.sece.examcraft.repositories.AttemptRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttemptService {

    private final AttemptRepository attemptRepository;
    private final TestPaperService testPaperService;

    public AttemptService(
            AttemptRepository attemptRepository,
            TestPaperService testPaperService) {

        this.attemptRepository = attemptRepository;
        this.testPaperService = testPaperService;
    }

    // Submit test and calculate score
    public Attempt submitAttempt(
            Attempt attempt,
            List<String> answers) {

        TestPaper testPaper =
                testPaperService.getTestPaperById(
                        attempt.getTestPaperId());

        if (testPaper == null) {
            throw new IllegalArgumentException(
                    "Test paper not found");
        }

        List<Question> questions =
                testPaper.getQuestions();

        if (answers == null ||
                answers.size() != questions.size()) {

            throw new IllegalArgumentException(
                    "Number of answers must match number of questions");
        }

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {

            Question question = questions.get(i);

            String correctAnswer =
                    question.getCorrectAnswer();

            String studentAnswer =
                    answers.get(i);

            if (studentAnswer != null &&
                    studentAnswer.equalsIgnoreCase(correctAnswer)) {

                score++;
            }
        }

        int totalQuestions = questions.size();

        String result;

        if (score >= Math.ceil(totalQuestions / 2.0)) {
            result = "Pass";
        } else {
            result = "Fail";
        }

        attempt.setScore(score);
        attempt.setTotalQuestions(totalQuestions);
        attempt.setResult(result);

        return attemptRepository.save(attempt);
    }

    // Add attempt
    public Attempt addAttempt(Attempt attempt) {
        return attemptRepository.save(attempt);
    }

    // Get all attempts
    public List<Attempt> getAllAttempts() {
        return attemptRepository.findAll();
    }

    // Get attempt by ID
    public Attempt getAttemptById(Long id) {
        return attemptRepository.findById(id).orElse(null);
    }

    // Delete attempt
    public void deleteAttempt(Long id) {
        attemptRepository.deleteById(id);
    }
}