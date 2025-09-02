package org.booleanuk.app.controller;

import org.booleanuk.app.model.dto.StudentDto;
import org.booleanuk.app.model.jpa.Student;
import org.booleanuk.app.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/public/student")
public class StudentPublicController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDto> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        List<StudentDto> result = new ArrayList<>();

        for (int i = 0; i < students.size(); i++){
            Student s = students.get(i);
            result.add(toDto(s));
        }

        return result;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto getStudentById(@PathVariable int id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "\t\n" +
                        "No student with that ID was found: " + id
        ));
        return toDto(student);
    }

    private StudentDto toDto(Student student){
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.isRetired()
        );
    }

}
