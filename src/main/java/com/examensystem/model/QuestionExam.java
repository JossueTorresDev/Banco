package com.examensystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "question_exam")
@IdClass(QuestionExamId.class)
public class QuestionExam {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @NotNull
    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal points = BigDecimal.ONE;

    // Constructors
    public QuestionExam() {}

    public QuestionExam(Exam exam, Question question, BigDecimal points) {
        this.exam = exam;
        this.question = question;
        this.points = points;
    }

    // Getters and Setters
    public Exam getExam() { return exam; }
    public void setExam(Exam exam) { this.exam = exam; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public BigDecimal getPoints() { return points; }
    public void setPoints(BigDecimal points) { this.points = points; }
}