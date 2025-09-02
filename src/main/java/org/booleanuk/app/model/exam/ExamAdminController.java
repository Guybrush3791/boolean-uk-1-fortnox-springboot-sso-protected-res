package org.booleanuk.app.model.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam")
public class ExamAdminController {
    
    @Autowired
    private ExamService service;

    @PostMapping
    public ResponseEntity<?> createExam(@RequestBody ExamDto dto) {
        return ResponseEntity.ok(service.addExam(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExam(@PathVariable int id, @RequestBody ExamDto dto) {

        if (service.updateExam(id, dto))
            return ResponseEntity.status(HttpStatus.CREATED).body("Updated exam information.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not update exam.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable int id) {

        if (service.deleteExam(id))
            return ResponseEntity.ok("Exam deleted.");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam not found.");
    }
}
