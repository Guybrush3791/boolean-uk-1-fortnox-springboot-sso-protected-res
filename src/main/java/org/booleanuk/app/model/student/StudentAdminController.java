package org.booleanuk.app.model.student;

import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentAdminController {

    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentDto dto) {
        Student s = new Student(
                dto.getFirst_name(),
                dto.getLast_name(),
                dto.getEmail(),
                dto.isRetired()
        );

        return ResponseEntity.ok(service.addStudent(s));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody StudentDto dto) {

        if (service.updateStudent(id, dto))
            return ResponseEntity.status(HttpStatus.CREATED).body("Updated student information.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not update student.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {

        if (service.deleteStudent(id))
            return ResponseEntity.ok("Student deleted.");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }


}
