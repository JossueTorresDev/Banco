package com.examensystem.controller;

import com.examensystem.dto.StudentDTO;
import com.examensystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/active")
    public ResponseEntity<List<StudentDTO>> getActiveStudents() {
        List<StudentDTO> students = studentService.getActiveStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{email}")
    public ResponseEntity<StudentDTO> getStudentByEmail(@PathVariable String email) {
        return studentService.getStudentByEmail(email)
                .map(student -> ResponseEntity.ok(student))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        try {
            StudentDTO createdStudent = studentService.createStudent(studentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable String email, 
                                                   @Valid @RequestBody StudentDTO studentDTO) {
        try {
            StudentDTO updatedStudent = studentService.updateStudent(email, studentDTO);
            return ResponseEntity.ok(updatedStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String email) {
        try {
            studentService.deleteStudent(email);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> searchStudents(@RequestParam String name) {
        List<StudentDTO> students = studentService.searchStudentsByName(name);
        return ResponseEntity.ok(students);
    }
}