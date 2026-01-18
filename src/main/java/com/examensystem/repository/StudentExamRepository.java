package com.examensystem.repository;

import com.examensystem.model.StudentExam;
import com.examensystem.model.StudentExamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExam, StudentExamId> {
    
    List<StudentExam> findByStudent_Email(String email);
    
    List<StudentExam> findByExam_ExamId(Integer examId);
    
    @Query("SELECT se FROM StudentExam se WHERE se.student.email = :email AND se.completed = 'Y'")
    List<StudentExam> findCompletedExamsByStudent(@Param("email") String email);
    
    @Query("SELECT se FROM StudentExam se WHERE se.student.email = :email AND se.completed = 'N'")
    List<StudentExam> findPendingExamsByStudent(@Param("email") String email);
    
    @Query("SELECT se FROM StudentExam se WHERE se.exam.examId = :examId AND se.completed = 'Y'")
    List<StudentExam> findCompletedStudentsByExam(@Param("examId") Integer examId);
}