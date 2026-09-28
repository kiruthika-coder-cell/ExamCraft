package com.sece.examcraft.services;

import com.sece.examcraft.entities.Question;
import com.sece.examcraft.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // Add question
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    // Get all questions
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // Get question by ID
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    // Filter by unit and difficulty
    public List<Question> getQuestionsByUnitAndDifficulty(
            String unit,
            String difficulty) {

        return questionRepository.findByUnitAndDifficulty(
                unit, difficulty);
    }

    // Filter by unit and topic
    public List<Question> getQuestionsByUnitAndTopic(
            String unit,
            String topic) {

        return questionRepository.findByUnitAndTopic(
                unit, topic);
    }

    // Filter by unit, topic and difficulty
    public List<Question> getQuestionsByUnitTopicAndDifficulty(
            String unit,
            String topic,
            String difficulty) {

        return questionRepository.findByUnitAndTopicAndDifficulty(
                unit, topic, difficulty);
    }

    // Filter by multiple topics and difficulty
    public List<Question> getQuestionsByUnitTopicsAndDifficulty(
            String unit,
            List<String> topics,
            String difficulty) {

        return questionRepository.findByUnitAndTopicInAndDifficulty(
                unit,
                topics,
                difficulty);
    }

    // Generate questions using difficulty percentage
    public List<Question> generateQuestions(
            String unit,
            int totalQuestions,
            int easyPercentage,
            int mediumPercentage,
            int hardPercentage) {

        // Validate question count
        if (totalQuestions <= 0) {
            throw new IllegalArgumentException(
                    "Question count must be greater than 0");
        }

        // Validate percentages
        if (easyPercentage < 0 ||
                mediumPercentage < 0 ||
                hardPercentage < 0) {

            throw new IllegalArgumentException(
                    "Difficulty percentages cannot be negative");
        }

        // Percentages must equal 100
        if (easyPercentage + mediumPercentage + hardPercentage != 100) {

            throw new IllegalArgumentException(
                    "Difficulty percentages must add up to 100");
        }

        // Calculate required questions
        int easyCount =
                totalQuestions * easyPercentage / 100;

        int mediumCount =
                totalQuestions * mediumPercentage / 100;

        int hardCount =
                totalQuestions - easyCount - mediumCount;


        // Get Easy questions
        List<Question> easyQuestions =
                questionRepository.findByUnitAndDifficulty(
                        unit,
                        "Easy");


        // Get Medium questions
        List<Question> mediumQuestions =
                questionRepository.findByUnitAndDifficulty(
                        unit,
                        "Medium");


        // Get Hard questions
        List<Question> hardQuestions =
                questionRepository.findByUnitAndDifficulty(
                        unit,
                        "Hard");


        // Check Easy availability
        if (easyQuestions.size() < easyCount) {

            throw new IllegalArgumentException(
                    "Not enough Easy questions available. Required: "
                            + easyCount
                            + ", Available: "
                            + easyQuestions.size());
        }


        // Check Medium availability
        if (mediumQuestions.size() < mediumCount) {

            throw new IllegalArgumentException(
                    "Not enough Medium questions available. Required: "
                            + mediumCount
                            + ", Available: "
                            + mediumQuestions.size());
        }


        // Check Hard availability
        if (hardQuestions.size() < hardCount) {

            throw new IllegalArgumentException(
                    "Not enough Hard questions available. Required: "
                            + hardCount
                            + ", Available: "
                            + hardQuestions.size());
        }


        // Shuffle questions
        Collections.shuffle(easyQuestions);
        Collections.shuffle(mediumQuestions);
        Collections.shuffle(hardQuestions);


        // Final generated list
        List<Question> generatedQuestions =
                new ArrayList<>();


        // Add Easy questions
        for (int i = 0; i < easyCount; i++) {

            generatedQuestions.add(
                    easyQuestions.get(i));
        }


        // Add Medium questions
        for (int i = 0; i < mediumCount; i++) {

            generatedQuestions.add(
                    mediumQuestions.get(i));
        }


        // Add Hard questions
        for (int i = 0; i < hardCount; i++) {

            generatedQuestions.add(
                    hardQuestions.get(i));
        }


        // Check for duplicate questions
        List<Long> questionIds =
                new ArrayList<>();

        for (Question question : generatedQuestions) {

            if (questionIds.contains(question.getId())) {

                throw new IllegalArgumentException(
                        "Duplicate question detected in generated test");
            }

            questionIds.add(question.getId());
        }


        // Shuffle final question order
        Collections.shuffle(generatedQuestions);

        return generatedQuestions;
    }


    // Update question
    public Question updateQuestion(
            Long id,
            Question question) {

        Question existingQuestion =
                questionRepository.findById(id).orElse(null);

        if (existingQuestion == null) {
            return null;
        }

        existingQuestion.setQuestionText(
                question.getQuestionText());

        existingQuestion.setOptionA(
                question.getOptionA());

        existingQuestion.setOptionB(
                question.getOptionB());

        existingQuestion.setOptionC(
                question.getOptionC());

        existingQuestion.setOptionD(
                question.getOptionD());

        existingQuestion.setCorrectAnswer(
                question.getCorrectAnswer());

        existingQuestion.setDifficulty(
                question.getDifficulty());

        existingQuestion.setUnit(
                question.getUnit());

        existingQuestion.setTopic(
                question.getTopic());

        return questionRepository.save(
                existingQuestion);
    }


    // Delete question
    public void deleteQuestion(Long id) {

        questionRepository.deleteById(id);
    }
}