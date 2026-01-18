package com.examensystem.repository;

import com.examensystem.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {
    
    List<Exam> findByStatus(String status);
    
    @Query("SELECT e FROM Exam e WHERE e.status = 'A'")
    List<Exam> findActiveExams();
    
    List<Exam> findByExamDate(LocalDate examDate);
    
    @Query("SELECT e FROM Exam e WHERE e.examDate BETWEEN :startDate AND :endDate")
    List<Exam> findByExamDateBetween(@Param("startDate") LocalDate startDate, 
                                     @Param("endDate") LocalDate endDate);
    
    @Query("SELECT e FROM Exam e WHERE e.name LIKE %:name%")
    List<Exam> findByNameContaining(@Param("name") String name);
}