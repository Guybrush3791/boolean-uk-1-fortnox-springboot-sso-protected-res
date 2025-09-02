package org.booleanuk.app.controller;

import org.booleanuk.app.model.jpa.dto.ExamDto;
import org.booleanuk.app.model.jpa.dto.StudentDto;
import org.booleanuk.app.model.jpa.pojo.Exam;
import org.booleanuk.app.model.jpa.pojo.Student;
import org.booleanuk.app.model.jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentAdminController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDto.StudentResponseDto> getAll() {
        return studentService.getAll().stream()
                .map(StudentDto.StudentResponseDto::new)
                .toList();
    }
    @GetMapping("{id}")
    public ResponseEntity<StudentDto.StudentResponseDto> getById(@PathVariable int id) {
        Student student = studentService.getById(id);
        return   student != null ?
                ResponseEntity.ok(new StudentDto.StudentResponseDto(student)):
                ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<StudentDto.StudentResponseDto> createExam(
            @RequestBody StudentDto.CreateStudentDto createStudent) {
        Student student = studentService.save(createStudent);
        return new ResponseEntity<>(new StudentDto.StudentResponseDto(student),HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<StudentDto.StudentResponseDto> updateExam(
            @PathVariable int id, @RequestBody StudentDto.UpdateStudentDto updateStudent) {
        Student student = studentService.updateStudent(id, updateStudent);
        if(student != null) {
            return ResponseEntity.ok(new StudentDto.StudentResponseDto(student));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteExam(@PathVariable int id) {
        Student student = studentService.delete(id);
        return student != null ?
                ResponseEntity.ok(student):
                ResponseEntity.notFound().build();
    }
}
