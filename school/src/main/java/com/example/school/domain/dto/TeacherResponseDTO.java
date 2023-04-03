package com.example.school.domain.dto;


import com.example.school.domain.entity.Teacher;
import lombok.Data;

@Data
public class TeacherResponseDTO {
    private String id;
    private String name;

    public TeacherResponseDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
    }
}
