package com.examensystem.service;

import com.examensystem.dto.StudentDTO;
import com.examensystem.model.Student;
import com.examensystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<StudentDTO> getActiveStudents() {
        return studentRepository.findActiveStudents().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<StudentDTO> getStudentByEmail(String email) {
        return studentRepository.findById(email)
                .map(this::convertToDTO);
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        if (studentRepository.existsById(studentDTO.getEmail())) {
            throw new RuntimeException("Student with email " + studentDTO.getEmail() + " already exists");
        }
        
        Student student = convertToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }

    public StudentDTO updateStudent(String email, StudentDTO studentDTO) {
        Student student = studentRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("Student not found with email: " + email));

        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        if (studentDTO.getPassword() != null && !studentDTO.getPassword().isEmpty()) {
            student.setPassword(studentDTO.getPassword());
        }
        if (studentDTO.getStatus() != null) {
            student.setStatus(studentDTO.getStatus());
        }

        Student updatedStudent = studentRepository.save(student);
        return convertToDTO(updatedStudent);
    }

    public void deleteStudent(String email) {
        if (!studentRepository.existsById(email)) {
            throw new RuntimeException("Student not found with email: " + email);
        }
        studentRepository.deleteById(email);
    }

    public List<StudentDTO> searchStudentsByName(String name) {
        return studentRepository.findByNameContaining(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private StudentDTO convertToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setEmail(student.getEmail());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setStatus(student.getStatus());
        // Don't include password in DTO for security
        return dto;
    }

    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setEmail(dto.getEmail());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setPassword(dto.getPassword());
        student.setStatus(dto.getStatus() != null ? dto.getStatus() : "A");
        return student;
    }
}