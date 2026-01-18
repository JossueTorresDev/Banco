package com.examensystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer teacherId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email
    @Size(max = 255)
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String password;

    @Column(length = 1, columnDefinition = "CHAR(1)")
    private String status = "A";

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses;

    // Constructors
    public Teacher() {}

    public Teacher(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public Integer getTeacherId() { return teacherId; }
    public void setTeacherId(Integer teacherId) { this.teacherId = teacherId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}