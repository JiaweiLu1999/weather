package com.example.school.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TeacherRequestDTO {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
}
