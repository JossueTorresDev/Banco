package com.examensystem.controller;

import com.examensystem.dto.ExamDTO;
import com.examensystem.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin(origins = "*")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping
    public ResponseEntity<List<ExamDTO>> getAllExams() {
        List<ExamDTO> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/active")
    public ResponseEntity<List<ExamDTO>> getActiveExams() {
        List<ExamDTO> exams = examService.getActiveExams();
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> getExamById(@PathVariable Integer id) {
        return examService.getExamById(id)
                .map(exam -> ResponseEntity.ok(exam))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<ExamDTO>> getExamsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<ExamDTO> exams = examService.getExamsByDate(date);
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ExamDTO>> getExamsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ExamDTO> exams = examService.getExamsByDateRange(startDate, endDate);
        return ResponseEntity.ok(exams);
    }

    @PostMapping
    public ResponseEntity<ExamDTO> createExam(@Valid @RequestBody ExamDTO examDTO) {
        try {
            ExamDTO createdExam = examService.createExam(examDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExam);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> updateExam(@PathVariable Integer id, 
                                             @Valid @RequestBody ExamDTO examDTO) {
        try {
            ExamDTO updatedExam = examService.updateExam(id, examDTO);
            return ResponseEntity.ok(updatedExam);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Integer id) {
        try {
            examService.deleteExam(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ExamDTO>> searchExams(@RequestParam String name) {
        List<ExamDTO> exams = examService.searchExamsByName(name);
        return ResponseEntity.ok(exams);
    }
}