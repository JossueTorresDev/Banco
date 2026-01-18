package com.examensystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class QuestionOptionDTO {
    private Integer optionId;

    private Integer questionId;

    @NotBlank(message = "Option text is required")
    @Size(max = 255, message = "Option text must not exceed 255 characters")
    private String optionText;

    private String isCorrect;

    // Constructors
    public QuestionOptionDTO() {}

    public QuestionOptionDTO(Integer questionId, String optionText, String isCorrect) {
        this.questionId = questionId;
        this.optionText = optionText;
        this.isCorrect = isCorrect;
    }

    // Getters and Setters
    public Integer getOptionId() { return optionId; }
    public void setOptionId(Integer optionId) { this.optionId = optionId; }

    public Integer getQuestionId() { return questionId; }
    public void setQuestionId(Integer questionId) { this.questionId = questionId; }

    public String getOptionText() { return optionText; }
    public void setOptionText(String optionText) { this.optionText = optionText; }

    public String getIsCorrect() { return isCorrect; }
    public void setIsCorrect(String isCorrect) { this.isCorrect = isCorrect; }
}