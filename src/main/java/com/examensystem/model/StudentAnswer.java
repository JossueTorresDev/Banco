package com.examensystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "student_answer")
@IdClass(StudentAnswerId.class)
public class StudentAnswer {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private Student student;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private QuestionOption option;

    @Column(name = "open_answer", columnDefinition = "TEXT")
    private String openAnswer;

    @Column(name = "answer_date")
    private LocalDate answerDate = LocalDate.now();

    // Constructors
    public StudentAnswer() {}

    public StudentAnswer(Student student, Exam exam, Question question) {
        this.student = student;
        this.exam = exam;
        this.question = question;
    }

    // Getters and Setters
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Exam getExam() { return exam; }
    public void setExam(Exam exam) { this.exam = exam; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public QuestionOption getOption() { return option; }
    public void setOption(QuestionOption option) { this.option = option; }

    public String getOpenAnswer() { return openAnswer; }
    public void setOpenAnswer(String openAnswer) { this.openAnswer = openAnswer; }

    public LocalDate getAnswerDate() { return answerDate; }
    public void setAnswerDate(LocalDate answerDate) { this.answerDate = answerDate; }
}