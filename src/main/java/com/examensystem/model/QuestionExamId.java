package com.examensystem.model;

import java.io.Serializable;
import java.util.Objects;

public class QuestionExamId implements Serializable {
    private Integer exam;
    private Integer question;

    public QuestionExamId() {}

    public QuestionExamId(Integer exam, Integer question) {
        this.exam = exam;
        this.question = question;
    }

    public Integer getExam() { return exam; }
    public void setExam(Integer exam) { this.exam = exam; }

    public Integer getQuestion() { return question; }
    public void setQuestion(Integer question) { this.question = question; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionExamId that = (QuestionExamId) o;
        return Objects.equals(exam, that.exam) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exam, question);
    }
}