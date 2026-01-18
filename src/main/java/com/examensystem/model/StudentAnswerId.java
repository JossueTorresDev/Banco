package com.examensystem.model;

import java.io.Serializable;
import java.util.Objects;

public class StudentAnswerId implements Serializable {
    private String student;
    private Integer exam;
    private Integer question;

    public StudentAnswerId() {}

    public StudentAnswerId(String student, Integer exam, Integer question) {
        this.student = student;
        this.exam = exam;
        this.question = question;
    }

    public String getStudent() { return student; }
    public void setStudent(String student) { this.student = student; }

    public Integer getExam() { return exam; }
    public void setExam(Integer exam) { this.exam = exam; }

    public Integer getQuestion() { return question; }
    public void setQuestion(Integer question) { this.question = question; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAnswerId that = (StudentAnswerId) o;
        return Objects.equals(student, that.student) && 
               Objects.equals(exam, that.exam) && 
               Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, exam, question);
    }
}