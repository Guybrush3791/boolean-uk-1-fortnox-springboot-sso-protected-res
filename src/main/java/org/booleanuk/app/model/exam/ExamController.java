package org.booleanuk.app.model.exam;

import org.booleanuk.app.model.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/exam")
public class ExamController {

    @Autowired
    private ExamService service;

    @GetMapping
    public ResponseEntity<?> getAllExams() {
        return ResponseEntity.ok(service.getAllExams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getASingleExam(@PathVariable int id) {
        ExamDto examDto = service.getSingleExam(id);

        return ResponseEntity.ok(examDto);
    }

}
