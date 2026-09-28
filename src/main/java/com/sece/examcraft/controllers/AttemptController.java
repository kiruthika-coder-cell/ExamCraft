package com.sece.examcraft.controllers;

import com.sece.examcraft.entities.Attempt;
import com.sece.examcraft.services.AttemptService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attempts")
public class AttemptController {

    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    // Submit test and auto-grade
    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.CREATED)
    public Attempt submitAttempt(
            @RequestBody SubmitAttemptRequest request) {

        return attemptService.submitAttempt(
                request.getAttempt(),
                request.getAnswers()
        );
    }

    // Get all attempts
    @GetMapping
    public List<Attempt> getAllAttempts() {
        return attemptService.getAllAttempts();
    }

    // Get attempt by ID
    @GetMapping("/{id}")
    public Attempt getAttemptById(
            @PathVariable Long id) {

        return attemptService.getAttemptById(id);
    }

    // Delete attempt
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAttempt(
            @PathVariable Long id) {

        attemptService.deleteAttempt(id);
    }

    // Request class
    public static class SubmitAttemptRequest {

        private Attempt attempt;
        private List<String> answers;

        public SubmitAttemptRequest() {
        }

        public Attempt getAttempt() {
            return attempt;
        }

        public void setAttempt(Attempt attempt) {
            this.attempt = attempt;
        }

        public List<String> getAnswers() {
            return answers;
        }

        public void setAnswers(List<String> answers) {
            this.answers = answers;
        }
    }
}