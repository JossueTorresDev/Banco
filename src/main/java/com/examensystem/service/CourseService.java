package com.examensystem.service;

import com.examensystem.dto.CourseDTO;
import com.examensystem.model.Course;
import com.examensystem.model.Teacher;
import com.examensystem.repository.CourseRepository;
import com.examensystem.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CourseDTO> getActiveCourses() {
        return courseRepository.findActiveCourses().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CourseDTO> getCourseById(Integer id) {
        return courseRepository.findById(id)
                .map(this::convertToDTO);
    }

    public List<CourseDTO> getCoursesByTeacher(Integer teacherId) {
        return courseRepository.findActiveCoursesByTeacher(teacherId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        if (courseRepository.existsByName(courseDTO.getName())) {
            throw new RuntimeException("Course with name " + courseDTO.getName() + " already exists");
        }

        Teacher teacher = teacherRepository.findById(courseDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + courseDTO.getTeacherId()));

        Course course = convertToEntity(courseDTO, teacher);
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    public CourseDTO updateCourse(Integer id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        
        if (courseDTO.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(courseDTO.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + courseDTO.getTeacherId()));
            course.setTeacher(teacher);
        }
        
        if (courseDTO.getStatus() != null) {
            course.setStatus(courseDTO.getStatus());
        }

        Course updatedCourse = courseRepository.save(course);
        return convertToDTO(updatedCourse);
    }

    public void deleteCourse(Integer id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    public List<CourseDTO> searchCoursesByName(String name) {
        return courseRepository.findByNameContaining(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setCourseId(course.getCourseId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setStatus(course.getStatus());
        
        if (course.getTeacher() != null) {
            dto.setTeacherId(course.getTeacher().getTeacherId());
            dto.setTeacherName(course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName());
        }
        
        return dto;
    }

    private Course convertToEntity(CourseDTO dto, Teacher teacher) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setTeacher(teacher);
        course.setStatus(dto.getStatus() != null ? dto.getStatus() : "A");
        return course;
    }
}