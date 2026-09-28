package com.sece.examcraft.controllers;

import com.sece.examcraft.entities.Question;
import com.sece.examcraft.services.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question addQuestion(
            @Valid @RequestBody Question question) {

        return questionService.addQuestion(question);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public Question getQuestionById(
            @PathVariable Long id) {

        return questionService.getQuestionById(id);
    }

    @GetMapping("/filter")
    public List<Question> getQuestionsByUnitAndDifficulty(
            @RequestParam String unit,
            @RequestParam String difficulty) {

        return questionService.getQuestionsByUnitAndDifficulty(
                unit,
                difficulty
        );
    }

    @GetMapping("/topic")
    public List<Question> getQuestionsByUnitAndTopic(
            @RequestParam String unit,
            @RequestParam String topic) {

        return questionService.getQuestionsByUnitAndTopic(
                unit,
                topic
        );
    }

    @GetMapping("/topic-difficulty")
    public List<Question> getQuestionsByUnitTopicAndDifficulty(
            @RequestParam String unit,
            @RequestParam String topic,
            @RequestParam String difficulty) {

        return questionService.getQuestionsByUnitTopicAndDifficulty(
                unit,
                topic,
                difficulty
        );
    }

    @PutMapping("/{id}")
    public Question updateQuestion(
            @PathVariable Long id,
            @Valid @RequestBody Question question) {

        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(
            @PathVariable Long id) {

        questionService.deleteQuestion(id);
    }
    @GetMapping("/count")
    public long getQuestionCount() {
        return questionService.getAllQuestions().size();
    }
}