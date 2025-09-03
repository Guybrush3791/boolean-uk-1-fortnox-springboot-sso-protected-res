package org.booleanuk.app.controller;

import org.booleanuk.app.model.dto.StudentDto;
import org.booleanuk.app.model.jpa.Student;
import org.booleanuk.app.model.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/student")
public class StudentAdminController {

    private final StudentRepository studentRepository;

    public StudentAdminController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto create(@RequestBody StudentDto dto){
        if (dto.firstName() == null || dto.firstName().isBlank() ||
            dto.lastName() == null || dto.lastName().isBlank() ||
            dto.email() == null || dto.email().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Could not create student, please check all required fields are correct."
            );
        }

        Student saved = studentRepository.save(new Student(
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.retired()
        ));

        return toDto(saved);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto updateStudent(@RequestBody StudentDto dto, @PathVariable int id){
        Student toUpdate = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student with id " + id + " not found."
                ));
        if (dto.firstName() == null || dto.firstName().isBlank() ||
            dto.lastName() == null || dto.lastName().isBlank() ||
            dto.email() == null || dto.email().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Could not update the student, please check all required fields are correct."
            );
        }

        toUpdate.setFirstName(dto.firstName());
        toUpdate.setLastName(dto.lastName());
        toUpdate.setEmail(dto.email());
        toUpdate.setRetired(dto.retired());

        Student saved = studentRepository.save(toUpdate);
        return toDto(saved);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto deleteStudent(@PathVariable int id){
        Student toDelete = studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "\t\n" +
                        "No student with that ID was found: " + id
        ));

        StudentDto dto = toDto(toDelete);
        studentRepository.delete(toDelete);

        return dto;

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
