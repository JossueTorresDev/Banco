package com.examensystem.repository;

import com.examensystem.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
    List<Question> findByStatus(String status);
    
    @Query("SELECT q FROM Question q WHERE q.status = 'A'")
    List<Question> findActiveQuestions();
    
    List<Question> findByCourse_CourseId(Integer courseId);
    
    @Query("SELECT q FROM Question q WHERE q.course.courseId = :courseId AND q.status = 'A'")
    List<Question> findActiveQuestionsByCourse(@Param("courseId") Integer courseId);
    
    List<Question> findByQuestionType(String questionType);
    
    @Query("SELECT q FROM Question q WHERE q.questionText LIKE %:text%")
    List<Question> findByQuestionTextContaining(@Param("text") String text);
}