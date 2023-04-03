package com.example.school.service;

import com.example.school.domain.dto.StudentRequestDTO;
import com.example.school.domain.dto.StudentResponseDTO;
import com.example.school.domain.dto.TeacherResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface StudentService {
    ResponseEntity<StudentResponseDTO> getById(String id);

    ResponseEntity<Collection<StudentResponseDTO>> getAll();

    ResponseEntity<String> create(StudentRequestDTO requestDTO);

    ResponseEntity<String> update(StudentRequestDTO requestDTO);

    ResponseEntity<Collection<TeacherResponseDTO>> getTeachers(String id);
}
