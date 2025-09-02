package org.booleanuk.app.model.controller;

import org.booleanuk.app.model.dto.StudentExamDto;
import org.booleanuk.app.model.dto.StudentResponseDto;
import org.booleanuk.app.model.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/public/student")
public class StudentPublicController {
    private final StudentService studentService;

    @Autowired
    public StudentPublicController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Get all students -> if no students, return empty list
    @GetMapping
    public ResponseEntity<List<StudentExamDto.StudentDto>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    // Get student by ID -> if not found, return 404 not found
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        StudentExamDto.StudentDto student;
        try {
            student = studentService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(student);
    }
}
