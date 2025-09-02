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
    public List<ExamDto> getAll() {
        return examService.getAll().stream()
                .map(ExamDto::new)
                .toList();
    }
    @GetMapping("{id}")
    public ResponseEntity<Exam> getById(@PathVariable int id) {
        Exam exam = examService.getById(id);
        return   exam != null ?
                ResponseEntity.ok(exam):
                ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        examService.save(exam);
        return new ResponseEntity<>(exam,HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable int id, @RequestBody Exam exam) {
        Exam foundExam = examService.getById(id);
        if(exam != null) {
            foundExam.setName(exam.getName());
            foundExam.setGrade(exam.getGrade());
            foundExam.setEcts(exam.getEcts());
            foundExam.setDate(exam.getDate());
            foundExam.setStudent(exam.getStudent());
            examService.save(foundExam);
            return ResponseEntity.ok(foundExam);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Exam> deleteExam(@PathVariable int id, @RequestBody Exam exam) {
        Exam foundExam = examService.getById(id);
        if(exam != null) {
            examService.delete(foundExam);
            return ResponseEntity.ok(foundExam);
        }
        return ResponseEntity.notFound().build();
    }
}
