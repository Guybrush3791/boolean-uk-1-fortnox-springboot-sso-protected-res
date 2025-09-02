package org.booleanuk.app.model.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/public/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getASingleStudent(@PathVariable int id) {
        Student s = service.getSingleStudent(id);

        if (s != null)
            return ResponseEntity.ok(s);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find student.");
    }

}
