package org.booleanuk.app.model.controller;

import org.booleanuk.app.model.dto.ExamDto;
import org.booleanuk.app.model.dto.StudentExamDto;
import org.booleanuk.app.model.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/exam")
public class ExamAdminController {
    private final ExamService examService;

    @Autowired
    public ExamAdminController(ExamService examService) {
        this.examService = examService;
    }

    // Save exam -> if error, return 400 bad request
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ExamDto exam) {
        StudentExamDto.ExamDto examResponse;
        try {
            examResponse = examService.save(exam);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(examResponse);
    }

    // Update exam -> if not found, return 400 bad request
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ExamDto exam) {
        StudentExamDto.ExamDto examResponse;
        try {
            examResponse = examService.update(id, exam);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(examResponse);
    }

    // Delete exam -> if not found, return 404 not found
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        StudentExamDto.ExamDto exam;
        try {
            exam = examService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(exam);
    }

}
