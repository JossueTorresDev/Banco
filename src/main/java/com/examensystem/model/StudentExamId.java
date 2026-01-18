package com.examensystem.model;

import java.io.Serializable;
import java.util.Objects;

public class StudentExamId implements Serializable {
    private String student;
    private Integer exam;

    public StudentExamId() {}

    public StudentExamId(String student, Integer exam) {
        this.student = student;
        this.exam = exam;
    }

    public String getStudent() { return student; }
    public void setStudent(String student) { this.student = student; }

    public Integer getExam() { return exam; }
    public void setExam(Integer exam) { this.exam = exam; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentExamId that = (StudentExamId) o;
        return Objects.equals(student, that.student) && Objects.equals(exam, that.exam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, exam);
    }
}