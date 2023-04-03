package com.example.school.domain.dto;

import com.example.school.domain.entity.Student;
import lombok.Data;

@Data
public class StudentResponseDTO {
    private String id;
    private String name;

    public StudentResponseDTO(Student student) {
        this.id = student.getId();
        this.name = student.getName();
    }
}
