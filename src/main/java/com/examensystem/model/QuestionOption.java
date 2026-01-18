package com.examensystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "question_option")
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Integer optionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @NotBlank
    @Size(max = 255)
    @Column(name = "option_text", nullable = false)
    private String optionText;

    @Column(name = "is_correct", length = 1, nullable = false, columnDefinition = "CHAR(1)")
    private String isCorrect;

    // Constructors
    public QuestionOption() {}

    public QuestionOption(Question question, String optionText, String isCorrect) {
        this.question = question;
        this.optionText = optionText;
        this.isCorrect = isCorrect;
    }

    // Getters and Setters
    public Integer getOptionId() { return optionId; }
    public void setOptionId(Integer optionId) { this.optionId = optionId; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public String getOptionText() { return optionText; }
    public void setOptionText(String optionText) { this.optionText = optionText; }

    public String getIsCorrect() { return isCorrect; }
    public void setIsCorrect(String isCorrect) { this.isCorrect = isCorrect; }
}