package com.examensystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExamDTO {
    private Integer examId;

    @NotBlank(message = "Exam name is required")
    @Size(max = 255, message = "Exam name must not exceed 255 characters")
    private String name;

    @NotNull(message = "Passing grade is required")
    private BigDecimal passingGrade;

    @NotNull(message = "Exam date is required")
    private LocalDate examDate;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    private String showResult;
    private String instructions;
    private String status;

    // Constructors
    public ExamDTO() {}

    public ExamDTO(String name, BigDecimal passingGrade, LocalDate examDate, 
                   LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.passingGrade = passingGrade;
        this.examDate = examDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public Integer getExamId() { return examId; }
    public void setExamId(Integer examId) { this.examId = examId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPassingGrade() { return passingGrade; }
    public void setPassingGrade(BigDecimal passingGrade) { this.passingGrade = passingGrade; }

    public LocalDate getExamDate() { return examDate; }
    public void setExamDate(LocalDate examDate) { this.examDate = examDate; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public String getShowResult() { return showResult; }
    public void setShowResult(String showResult) { this.showResult = showResult; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}