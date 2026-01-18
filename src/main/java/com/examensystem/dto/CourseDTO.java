package com.examensystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CourseDTO {
    private Integer courseId;

    @NotBlank(message = "Course name is required")
    @Size(max = 100, message = "Course name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    private Integer teacherId;
    private String teacherName;
    private String status;

    // Constructors
    public CourseDTO() {}

    public CourseDTO(String name, String description, Integer teacherId) {
        this.name = name;
        this.description = description;
        this.teacherId = teacherId;
    }

    // Getters and Setters
    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getTeacherId() { return teacherId; }
    public void setTeacherId(Integer teacherId) { this.teacherId = teacherId; }

    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}