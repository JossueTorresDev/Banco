package com.examensystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class QuestionDTO {
    private Integer questionId;

    @NotBlank(message = "Question text is required")
    private String questionText;

    @NotBlank(message = "Question type is required")
    @Size(max = 50, message = "Question type must not exceed 50 characters")
    private String questionType;

    @NotNull(message = "Course ID is required")
    private Integer courseId;

    private String courseName;
    private LocalDate creationDate;
    private String status;

    @Size(max = 2000, message = "Feedback text must not exceed 2000 characters")
    private String feedbackText;

    private List<QuestionOptionDTO> options;

    // Constructors
    public QuestionDTO() {}

    public QuestionDTO(String questionText, String questionType, Integer courseId) {
        this.questionText = questionText;
        this.questionType = questionType;
        this.courseId = courseId;
    }

    // Getters and Setters
    public Integer getQuestionId() { return questionId; }
    public void setQuestionId(Integer questionId) { this.questionId = questionId; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getQuestionType() { return questionType; }
    public void setQuestionType(String questionType) { this.questionType = questionType; }

    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFeedbackText() { return feedbackText; }
    public void setFeedbackText(String feedbackText) { this.feedbackText = feedbackText; }

    public List<QuestionOptionDTO> getOptions() { return options; }
    public void setOptions(List<QuestionOptionDTO> options) { this.options = options; }
}