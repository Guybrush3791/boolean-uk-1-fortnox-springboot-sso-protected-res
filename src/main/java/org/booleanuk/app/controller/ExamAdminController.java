package org.booleanuk.app.controller;

import org.booleanuk.app.model.jpa.dto.ExamDto;
import org.booleanuk.app.model.jpa.pojo.Exam;
import org.booleanuk.app.model.jpa.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamAdminController {
    @Autowired
    private ExamService examService;

    @GetMapping
    public List<ExamDto.ExamResponseDto> getAll() {
        return examService.getAll().stream()
                .map(ExamDto.ExamResponseDto::new)
                .toList();
    }
    @GetMapping("{id}")
    public ResponseEntity<ExamDto.ExamResponseDto> getById(@PathVariable int id) {
        Exam exam = examService.getById(id);
        return   exam != null ?
                ResponseEntity.ok(new ExamDto.ExamResponseDto(exam)):
                ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<ExamDto.ExamResponseDto> createExam(@RequestBody ExamDto.CreateExamDto createExam) {
        Exam exam = examService.save(createExam);
        return new ResponseEntity<>(new ExamDto.ExamResponseDto(exam),HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<ExamDto.ExamResponseDto> updateExam(
            @PathVariable int id, @RequestBody ExamDto.UpdateExamDto updateExam) {
        Exam exam = examService.updateExam(id, updateExam);
        if(exam != null) {
            return ResponseEntity.ok(new ExamDto.ExamResponseDto(exam));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Exam> deleteExam(@PathVariable int id) {
        Exam exam = examService.delete(id);
        return exam != null ?
                ResponseEntity.ok(exam):
                ResponseEntity.notFound().build();
    }
}
