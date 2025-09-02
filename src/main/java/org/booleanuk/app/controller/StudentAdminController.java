package org.booleanuk.app.controller;

import org.booleanuk.app.model.jpa.dto.StudentDto;
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
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.getById(id);
        return student != null ?
                ResponseEntity.ok(student):
                ResponseEntity.notFound().build();

    }
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentDto studentDto) {
        Student student = new Student(studentDto);
        System.out.println(studentDto);
        studentService.save(student);
        System.out.println(student.getFirstName()+student.getLastName()+student.getEmail()+student.isRetired());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(
            @RequestBody StudentDto studentDto, @PathVariable int id) {
        Student student = studentService.getById(id);
        if(student != null) {
            student.setFirstName(studentDto.getFirstName());
            student.setLastName(studentDto.getLastName());
            student.setEmail(studentDto.getEmail());
            student.setRetired(studentDto.isRetired());
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        Student student = studentService.getById(id);
        if (student != null) {
            studentService.delete(student);
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }
}
