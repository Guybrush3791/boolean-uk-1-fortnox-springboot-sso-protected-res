package org.booleanuk.app.model.controller;

import org.booleanuk.app.model.dto.StudentDto;
import org.booleanuk.app.model.dto.StudentExamDto;
import org.booleanuk.app.model.dto.StudentResponseDto;
import org.booleanuk.app.model.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/student")
public class StudentAdminController {
    private final StudentService studentService;

    @Autowired
    public StudentAdminController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Save student -> if error, return 400 bad request
    @PostMapping
    public ResponseEntity<?> save(@RequestBody StudentDto student) {
        StudentExamDto.StudentDto studentResponse;
        try {
            studentResponse = studentService.save(student);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(studentResponse);
    }

    // Update student -> if not found, return 400 bad request
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody StudentDto student) {
        StudentExamDto.StudentDto studentResponse;
        try {
            studentResponse = studentService.update(id, student);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(studentResponse);
    }

    // Delete student -> if not found, return 404 not found
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        StudentExamDto.StudentDto student;
        try {
            student = studentService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
}
