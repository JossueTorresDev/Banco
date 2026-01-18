package com.examensystem.repository;

import com.examensystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    
    List<Course> findByStatus(String status);
    
    @Query("SELECT c FROM Course c WHERE c.status = 'A'")
    List<Course> findActiveCourses();
    
    List<Course> findByTeacherTeacherId(Integer teacherId);
    
    @Query("SELECT c FROM Course c WHERE c.teacher.teacherId = :teacherId AND c.status = 'A'")
    List<Course> findActiveCoursesByTeacher(@Param("teacherId") Integer teacherId);
    
    @Query("SELECT c FROM Course c WHERE c.name LIKE %:name%")
    List<Course> findByNameContaining(@Param("name") String name);
    
    boolean existsByName(String name);
}