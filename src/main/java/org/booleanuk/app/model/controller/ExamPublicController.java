package org.booleanuk.app.model.controller;

import org.booleanuk.app.model.dto.StudentExamDto;
import org.booleanuk.app.model.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public/exam")
public class ExamPublicController {
    private final ExamService examService;

    @Autowired
    public ExamPublicController(ExamService examService) {
        this.examService = examService;
    }

    // Get all students -> if no students, return empty list
    @GetMapping
    public ResponseEntity<List<StudentExamDto.ExamDto>> getAll() {
        return ResponseEntity.ok(examService.getAll());
    }

    // Get student by ID -> if not found, return 404 not found
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        StudentExamDto.ExamDto exam;
        try {
            exam = examService.getById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(exam);
    }


}
