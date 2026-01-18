package com.examensystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "student_exam")
@IdClass(StudentExamId.class)
public class StudentExam {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private Student student;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Column(name = "assigned_date")
    private LocalDate assignedDate = LocalDate.now();

    @Column(length = 1, columnDefinition = "CHAR(1)")
    private String completed = "N";

    // Constructors
    public StudentExam() {}

    public StudentExam(Student student, Exam exam) {
        this.student = student;
        this.exam = exam;
    }

    // Getters and Setters
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Exam getExam() { return exam; }
    public void setExam(Exam exam) { this.exam = exam; }

    public LocalDate getAssignedDate() { return assignedDate; }
    public void setAssignedDate(LocalDate assignedDate) { this.assignedDate = assignedDate; }

    public String getCompleted() { return completed; }
    public void setCompleted(String completed) { this.completed = completed; }
}