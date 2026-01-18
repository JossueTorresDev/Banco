package com.examensystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TeacherDTO {
    private Integer teacherId;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100, message = "El apellido no debe exceder 100 caracteres")
    private String lastName;

    @Email(message = "El email debe ser válido")
    @Size(max = 255, message = "El email no debe exceder 255 caracteres")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 255, message = "La contraseña no debe exceder 255 caracteres")
    private String password;

    private String status;

    // Constructors
    public TeacherDTO() {}

    public TeacherDTO(String firstName, String lastName, String email, String password) {
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
}