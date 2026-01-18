package com.examensystem.controller;

import com.examensystem.dto.QuestionDTO;
import com.examensystem.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        List<QuestionDTO> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/active")
    public ResponseEntity<List<QuestionDTO>> getActiveQuestions() {
        List<QuestionDTO> questions = questionService.getActiveQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Integer id) {
        return questionService.getQuestionById(id)
                .map(question -> ResponseEntity.ok(question))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByCourse(@PathVariable Integer courseId) {
        List<QuestionDTO> questions = questionService.getQuestionsByCourse(courseId);
        return ResponseEntity.ok(questions);
    }

    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        try {
            QuestionDTO createdQuestion = questionService.createQuestion(questionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable Integer id, 
                                                     @Valid @RequestBody QuestionDTO questionDTO) {
        try {
            QuestionDTO updatedQuestion = questionService.updateQuestion(id, questionDTO);
            return ResponseEntity.ok(updatedQuestion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        try {
            questionService.deleteQuestion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<QuestionDTO>> searchQuestions(@RequestParam String text) {
        List<QuestionDTO> questions = questionService.searchQuestionsByText(text);
        return ResponseEntity.ok(questions);
    }
}