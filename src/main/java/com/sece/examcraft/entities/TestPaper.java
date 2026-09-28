package com.sece.examcraft.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TestPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Test name is required")
    private String testName;

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotBlank(message = "Unit is required")
    private String unit;

    @Positive(message = "Question count must be positive")
    private Integer questionCount;

    @Min(value = 0, message = "Easy percentage cannot be negative")
    @Max(value = 100, message = "Easy percentage cannot exceed 100")
    private Integer easyPercentage;

    @Min(value = 0, message = "Medium percentage cannot be negative")
    @Max(value = 100, message = "Medium percentage cannot exceed 100")
    private Integer mediumPercentage;

    @Min(value = 0, message = "Hard percentage cannot be negative")
    @Max(value = 100, message = "Hard percentage cannot exceed 100")
    private Integer hardPercentage;

    @ManyToMany
    @JoinTable(
            name = "test_paper_questions",
            joinColumns = @JoinColumn(name = "test_paper_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions = new ArrayList<>();


    public TestPaper() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }


    public Integer getEasyPercentage() {
        return easyPercentage;
    }

    public void setEasyPercentage(Integer easyPercentage) {
        this.easyPercentage = easyPercentage;
    }


    public Integer getMediumPercentage() {
        return mediumPercentage;
    }

    public void setMediumPercentage(Integer mediumPercentage) {
        this.mediumPercentage = mediumPercentage;
    }


    public Integer getHardPercentage() {
        return hardPercentage;
    }

    public void setHardPercentage(Integer hardPercentage) {
        this.hardPercentage = hardPercentage;
    }


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}