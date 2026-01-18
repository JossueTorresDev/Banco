package com.examensystem.repository;

import com.examensystem.model.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Integer> {
    
    List<QuestionOption> findByQuestion_QuestionId(Integer questionId);
    
    @Query("SELECT qo FROM QuestionOption qo WHERE qo.question.questionId = :questionId AND qo.isCorrect = 'Y'")
    List<QuestionOption> findCorrectOptionsByQuestion(@Param("questionId") Integer questionId);
    
    @Query("SELECT qo FROM QuestionOption qo WHERE qo.question.questionId = :questionId")
    List<QuestionOption> findAllOptionsByQuestion(@Param("questionId") Integer questionId);
}