package org.booleanuk.app.controller;

import org.booleanuk.app.model.jpa.pojo.Student;
import org.booleanuk.app.model.jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/student")
public class StudentPublicController {
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
}
