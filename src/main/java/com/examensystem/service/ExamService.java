package com.examensystem.service;

import com.examensystem.dto.ExamDTO;
import com.examensystem.model.Exam;
import com.examensystem.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public List<ExamDTO> getAllExams() {
        return examRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ExamDTO> getActiveExams() {
        return examRepository.findActiveExams().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ExamDTO> getExamById(Integer id) {
        return examRepository.findById(id)
                .map(this::convertToDTO);
    }

    public List<ExamDTO> getExamsByDate(LocalDate date) {
        return examRepository.findByExamDate(date).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ExamDTO> getExamsByDateRange(LocalDate startDate, LocalDate endDate) {
        return examRepository.findByExamDateBetween(startDate, endDate).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ExamDTO createExam(ExamDTO examDTO) {
        Exam exam = convertToEntity(examDTO);
        Exam savedExam = examRepository.save(exam);
        return convertToDTO(savedExam);
    }

    public ExamDTO updateExam(Integer id, ExamDTO examDTO) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));

        exam.setName(examDTO.getName());
        exam.setPassingGrade(examDTO.getPassingGrade());
        exam.setExamDate(examDTO.getExamDate());
        exam.setStartTime(examDTO.getStartTime());
        exam.setEndTime(examDTO.getEndTime());
        exam.setInstructions(examDTO.getInstructions());
        
        if (examDTO.getShowResult() != null) {
            exam.setShowResult(examDTO.getShowResult());
        }
        if (examDTO.getStatus() != null) {
            exam.setStatus(examDTO.getStatus());
        }

        Exam updatedExam = examRepository.save(exam);
        return convertToDTO(updatedExam);
    }

    public void deleteExam(Integer id) {
        if (!examRepository.existsById(id)) {
            throw new RuntimeException("Exam not found with id: " + id);
        }
        examRepository.deleteById(id);
    }

    public List<ExamDTO> searchExamsByName(String name) {
        return examRepository.findByNameContaining(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ExamDTO convertToDTO(Exam exam) {
        ExamDTO dto = new ExamDTO();
        dto.setExamId(exam.getExamId());
        dto.setName(exam.getName());
        dto.setPassingGrade(exam.getPassingGrade());
        dto.setExamDate(exam.getExamDate());
        dto.setStartTime(exam.getStartTime());
        dto.setEndTime(exam.getEndTime());
        dto.setShowResult(exam.getShowResult());
        dto.setInstructions(exam.getInstructions());
        dto.setStatus(exam.getStatus());
        return dto;
    }

    private Exam convertToEntity(ExamDTO dto) {
        Exam exam = new Exam();
        exam.setName(dto.getName());
        exam.setPassingGrade(dto.getPassingGrade());
        exam.setExamDate(dto.getExamDate());
        exam.setStartTime(dto.getStartTime());
        exam.setEndTime(dto.getEndTime());
        exam.setInstructions(dto.getInstructions());
        exam.setShowResult(dto.getShowResult() != null ? dto.getShowResult() : "N");
        exam.setStatus(dto.getStatus() != null ? dto.getStatus() : "A");
        return exam;
    }
}