package com.examensystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;

    @NotBlank
    @Column(name = "question_text", nullable = false, columnDefinition = "TEXT")
    private String questionText;

    @NotBlank
    @Size(max = 50)
    @Column(name = "question_type", nullable = false)
    private String questionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate = LocalDate.now();

    @Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
    private String status = "A";

    @Size(max = 2000)
    @Column(name = "feedback_text")
    private String feedbackText;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuestionOption> options;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuestionExam> questionExams;

    // Constructors
    public Question() {}

    public Question(String questionText, String questionType, Course course) {
        this.questionText = questionText;
        this.questionType = questionType;
        this.course = course;
    }

    // Getters and Setters
    public Integer getQuestionId() { return questionId; }
    public void setQuestionId(Integer questionId) { this.questionId = questionId; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getQuestionType() { return questionType; }
    public void setQuestionType(String questionType) { this.questionType = questionType; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFeedbackText() { return feedbackText; }
    public void setFeedbackText(String feedbackText) { this.feedbackText = feedbackText; }

    public List<QuestionOption> getOptions() { return options; }
    public void setOptions(List<QuestionOption> options) { this.options = options; }

    public List<QuestionExam> getQuestionExams() { return questionExams; }
    public void setQuestionExams(List<QuestionExam> questionExams) { this.questionExams = questionExams; }
}