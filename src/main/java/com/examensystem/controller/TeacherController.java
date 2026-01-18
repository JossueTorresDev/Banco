package com.examensystem.controller;

import com.examensystem.dto.TeacherDTO;
import com.examensystem.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/active")
    public ResponseEntity<List<TeacherDTO>> getActiveTeachers() {
        List<TeacherDTO> teachers = teacherService.getActiveTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Integer id) {
        return teacherService.getTeacherById(id)
                .map(teacher -> ResponseEntity.ok(teacher))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<TeacherDTO> getTeacherByEmail(@PathVariable String email) {
        return teacherService.getTeacherByEmail(email)
                .map(teacher -> ResponseEntity.ok(teacher))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@Valid @RequestBody TeacherDTO teacherDTO) {
        try {
            TeacherDTO createdTeacher = teacherService.createTeacher(teacherDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Integer id, 
                                                   @Valid @RequestBody TeacherDTO teacherDTO) {
        try {
            TeacherDTO updatedTeacher = teacherService.updateTeacher(id, teacherDTO);
            return ResponseEntity.ok(updatedTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Integer id) {
        try {
            teacherService.deleteTeacher(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<TeacherDTO>> searchTeachers(@RequestParam String name) {
        List<TeacherDTO> teachers = teacherService.searchTeachersByName(name);
        return ResponseEntity.ok(teachers);
    }
}