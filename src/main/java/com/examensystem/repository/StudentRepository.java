package com.examensystem.repository;

import com.examensystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    
    List<Student> findByStatus(String status);
    
    @Query("SELECT s FROM Student s WHERE s.status = 'A'")
    List<Student> findActiveStudents();
    
    @Query("SELECT s FROM Student s WHERE s.firstName LIKE %:name% OR s.lastName LIKE %:name%")
    List<Student> findByNameContaining(@Param("name") String name);
}