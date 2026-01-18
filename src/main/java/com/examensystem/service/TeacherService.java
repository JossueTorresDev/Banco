package com.examensystem.service;

import com.examensystem.dto.TeacherDTO;
import com.examensystem.model.Teacher;
import com.examensystem.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<TeacherDTO> getActiveTeachers() {
        return teacherRepository.findActiveTeachers().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TeacherDTO> getTeacherById(Integer id) {
        return teacherRepository.findById(id)
                .map(this::convertToDTO);
    }

    public Optional<TeacherDTO> getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email)
                .map(this::convertToDTO);
    }

    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        if (teacherRepository.existsByEmail(teacherDTO.getEmail())) {
            throw new RuntimeException("Ya existe un profesor con el email " + teacherDTO.getEmail());
        }
        
        Teacher teacher = convertToEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return convertToDTO(savedTeacher);
    }

    public TeacherDTO updateTeacher(Integer id, TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el profesor con ID: " + id));

        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setEmail(teacherDTO.getEmail());
        if (teacherDTO.getPassword() != null && !teacherDTO.getPassword().isEmpty()) {
            teacher.setPassword(teacherDTO.getPassword());
        }
        if (teacherDTO.getStatus() != null) {
            teacher.setStatus(teacherDTO.getStatus());
        }

        Teacher updatedTeacher = teacherRepository.save(teacher);
        return convertToDTO(updatedTeacher);
    }

    public void deleteTeacher(Integer id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el profesor con ID: " + id);
        }
        teacherRepository.deleteById(id);
    }

    public List<TeacherDTO> searchTeachersByName(String name) {
        return teacherRepository.findByNameContaining(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setTeacherId(teacher.getTeacherId());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setEmail(teacher.getEmail());
        dto.setStatus(teacher.getStatus());
        // Don't include password in DTO for security
        return dto;
    }

    private Teacher convertToEntity(TeacherDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setEmail(dto.getEmail());
        teacher.setPassword(dto.getPassword());
        teacher.setStatus(dto.getStatus() != null ? dto.getStatus() : "A");
        return teacher;
    }
}