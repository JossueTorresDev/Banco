package com.examensystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(max = 100)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(max = 255)
    private String password;

    @Column(length = 1, columnDefinition = "CHAR(1)")
    private String status = "A";

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentExam> studentExams;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentAnswer> studentAnswers;

    // Constructors
    public Student() {}

    public Student(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<StudentExam> getStudentExams() { return studentExams; }
    public void setStudentExams(List<StudentExam> studentExams) { this.studentExams = studentExams; }

    public List<StudentAnswer> getStudentAnswers() { return studentAnswers; }
    public void setStudentAnswers(List<StudentAnswer> studentAnswers) { this.studentAnswers = studentAnswers; }
}