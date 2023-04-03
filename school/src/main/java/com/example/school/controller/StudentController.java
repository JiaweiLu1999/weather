package com.example.school.controller;

import com.example.school.domain.dto.StudentRequestDTO;
import com.example.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return studentService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StudentRequestDTO requestDTO) {
        return studentService.create(requestDTO);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody StudentRequestDTO requestDTO) {
        return studentService.update(requestDTO);
    }

    @GetMapping("/{id}/teacher")
    public ResponseEntity<?> findAllTeachersById(@PathVariable String id) {
        return studentService.getTeachers(id);
    }


}
