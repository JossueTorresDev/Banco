package com.examensystem.controller;

import com.examensystem.dto.CourseDTO;
import com.examensystem.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/active")
    public ResponseEntity<List<CourseDTO>> getActiveCourses() {
        List<CourseDTO> courses = courseService.getActiveCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id)
                .map(course -> ResponseEntity.ok(course))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<CourseDTO>> getCoursesByTeacher(@PathVariable Integer teacherId) {
        List<CourseDTO> courses = courseService.getCoursesByTeacher(teacherId);
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        try {
            CourseDTO createdCourse = courseService.createCourse(courseDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Integer id, 
                                                 @Valid @RequestBody CourseDTO courseDTO) {
        try {
            CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);
            return ResponseEntity.ok(updatedCourse);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseDTO>> searchCourses(@RequestParam String name) {
        List<CourseDTO> courses = courseService.searchCoursesByName(name);
        return ResponseEntity.ok(courses);
    }
}