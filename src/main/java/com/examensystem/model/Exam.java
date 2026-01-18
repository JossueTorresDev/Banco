package com.examensystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Integer examId;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(name = "passing_grade", nullable = false, precision = 4, scale = 2)
    private BigDecimal passingGrade;

    @NotNull
    @Column(name = "exam_date", nullable = false)
    private LocalDate examDate;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "show_result", length = 1, columnDefinition = "CHAR(1)")
    private String showResult = "N";

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Column(length = 1, columnDefinition = "CHAR(1)")
    private String status = "A";

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuestionExam> questionExams;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentExam> studentExams;

    // Constructors
    public Exam() {}

    public Exam(String name, BigDecimal passingGrade, LocalDate examDate, 
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

    public List<QuestionExam> getQuestionExams() { return questionExams; }
    public void setQuestionExams(List<QuestionExam> questionExams) { this.questionExams = questionExams; }

    public List<StudentExam> getStudentExams() { return studentExams; }
    public void setStudentExams(List<StudentExam> studentExams) { this.studentExams = studentExams; }
}