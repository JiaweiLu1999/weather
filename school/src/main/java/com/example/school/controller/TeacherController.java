package com.example.school.controller;

import com.example.school.domain.dto.TeacherRequestDTO;
import com.example.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return teacherService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TeacherRequestDTO requestDTO) {
        return teacherService.create(requestDTO);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TeacherRequestDTO requestDTO) {
        return teacherService.update(requestDTO);
    }

    @GetMapping("/{id}/student")
    public ResponseEntity<?> findAllTeachersById(@PathVariable String id) {
        return teacherService.getStudents(id);
    }

}
