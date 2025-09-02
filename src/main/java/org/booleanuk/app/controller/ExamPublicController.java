package org.booleanuk.app.controller;

import org.booleanuk.app.model.jpa.dto.ExamDto;
import org.booleanuk.app.model.jpa.pojo.Exam;
import org.booleanuk.app.model.jpa.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public/exams")
public class ExamPublicController {
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
}
