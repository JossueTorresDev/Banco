package com.examensystem.repository;

import com.examensystem.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    
    Optional<Teacher> findByEmail(String email);
    
    List<Teacher> findByStatus(String status);
    
    @Query("SELECT t FROM Teacher t WHERE t.status = 'A'")
    List<Teacher> findActiveTeachers();
    
    @Query("SELECT t FROM Teacher t WHERE t.firstName LIKE %:name% OR t.lastName LIKE %:name%")
    List<Teacher> findByNameContaining(@Param("name") String name);
    
    boolean existsByEmail(String email);
}