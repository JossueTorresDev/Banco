package com.examensystem.service;

import com.examensystem.dto.QuestionDTO;
import com.examensystem.dto.QuestionOptionDTO;
import com.examensystem.model.Course;
import com.examensystem.model.Question;
import com.examensystem.model.QuestionOption;
import com.examensystem.repository.CourseRepository;
import com.examensystem.repository.QuestionOptionRepository;
import com.examensystem.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionOptionRepository questionOptionRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<QuestionDTO> getActiveQuestions() {
        return questionRepository.findActiveQuestions().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<QuestionDTO> getQuestionById(Integer id) {
        return questionRepository.findById(id)
                .map(this::convertToDTO);
    }

    public List<QuestionDTO> getQuestionsByCourse(Integer courseId) {
        return questionRepository.findActiveQuestionsByCourse(courseId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        Course course = courseRepository.findById(questionDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + questionDTO.getCourseId()));

        Question question = convertToEntity(questionDTO, course);
        Question savedQuestion = questionRepository.save(question);

        // Save options if provided
        if (questionDTO.getOptions() != null && !questionDTO.getOptions().isEmpty()) {
            List<QuestionOption> options = questionDTO.getOptions().stream()
                    .map(optionDTO -> convertOptionToEntity(optionDTO, savedQuestion))
                    .collect(Collectors.toList());
            questionOptionRepository.saveAll(options);
        }

        return convertToDTO(savedQuestion);
    }

    @Transactional
    public QuestionDTO updateQuestion(Integer id, QuestionDTO questionDTO) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));

        question.setQuestionText(questionDTO.getQuestionText());
        question.setQuestionType(questionDTO.getQuestionType());
        question.setFeedbackText(questionDTO.getFeedbackText());
        
        if (questionDTO.getCourseId() != null) {
            Course course = courseRepository.findById(questionDTO.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found with id: " + questionDTO.getCourseId()));
            question.setCourse(course);
        }
        
        if (questionDTO.getStatus() != null) {
            question.setStatus(questionDTO.getStatus());
        }

        Question updatedQuestion = questionRepository.save(question);

        // Update options if provided
        if (questionDTO.getOptions() != null) {
            // Delete existing options
            List<QuestionOption> existingOptions = questionOptionRepository.findByQuestion_QuestionId(id);
            questionOptionRepository.deleteAll(existingOptions);
            
            // Save new options
            List<QuestionOption> newOptions = questionDTO.getOptions().stream()
                    .map(optionDTO -> convertOptionToEntity(optionDTO, updatedQuestion))
                    .collect(Collectors.toList());
            questionOptionRepository.saveAll(newOptions);
        }

        return convertToDTO(updatedQuestion);
    }

    public void deleteQuestion(Integer id) {
        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Question not found with id: " + id);
        }
        questionRepository.deleteById(id);
    }

    public List<QuestionDTO> searchQuestionsByText(String text) {
        return questionRepository.findByQuestionTextContaining(text).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private QuestionDTO convertToDTO(Question question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setQuestionId(question.getQuestionId());
        dto.setQuestionText(question.getQuestionText());
        dto.setQuestionType(question.getQuestionType());
        dto.setCreationDate(question.getCreationDate());
        dto.setStatus(question.getStatus());
        dto.setFeedbackText(question.getFeedbackText());
        
        if (question.getCourse() != null) {
            dto.setCourseId(question.getCourse().getCourseId());
            dto.setCourseName(question.getCourse().getName());
        }

        // Load options
        List<QuestionOption> options = questionOptionRepository.findByQuestion_QuestionId(question.getQuestionId());
        dto.setOptions(options.stream()
                .map(this::convertOptionToDTO)
                .collect(Collectors.toList()));
        
        return dto;
    }

    private Question convertToEntity(QuestionDTO dto, Course course) {
        Question question = new Question();
        question.setQuestionText(dto.getQuestionText());
        question.setQuestionType(dto.getQuestionType());
        question.setCourse(course);
        question.setFeedbackText(dto.getFeedbackText());
        question.setStatus(dto.getStatus() != null ? dto.getStatus() : "A");
        return question;
    }

    private QuestionOptionDTO convertOptionToDTO(QuestionOption option) {
        QuestionOptionDTO dto = new QuestionOptionDTO();
        dto.setOptionId(option.getOptionId());
        dto.setQuestionId(option.getQuestion().getQuestionId());
        dto.setOptionText(option.getOptionText());
        dto.setIsCorrect(option.getIsCorrect());
        return dto;
    }

    private QuestionOption convertOptionToEntity(QuestionOptionDTO dto, Question question) {
        QuestionOption option = new QuestionOption();
        option.setQuestion(question);
        option.setOptionText(dto.getOptionText());
        option.setIsCorrect(dto.getIsCorrect() != null ? dto.getIsCorrect() : "N");
        return option;
    }
}