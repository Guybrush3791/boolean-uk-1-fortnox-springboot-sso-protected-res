package org.booleanuk.app.model.service;

import org.booleanuk.app.model.dto.StudentDto;
import org.booleanuk.app.model.dto.StudentExamDto;
import org.booleanuk.app.model.dto.StudentResponseDto;
import org.booleanuk.app.model.jpa.Student;
import org.booleanuk.app.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Methods
    // Get all students
    public List<StudentExamDto.StudentDto> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(StudentExamDto.StudentDto::new)
                .toList();
    }

    // Get student by ID
    public StudentExamDto.StudentDto getById(int id) throws Exception {
        return studentRepository.findById(id)
                .map(StudentExamDto.StudentDto::new)
                .orElseThrow(
                        () -> new Exception("No student with that id was found.")
                );
    }

    // Save a new student
    public StudentExamDto.StudentDto save(StudentDto studentDto) throws Exception {
        Student student;
        try {
             student = studentRepository.save(new Student(studentDto));
        } catch (Exception e) {
            throw new Exception("Could not create student, please check all required fields are correct.");
        }
        return new StudentExamDto.StudentDto(student);
    }

    // Update an existing student
    public StudentExamDto.StudentDto update(int id, StudentDto studentDto) throws Exception {
        var student = studentRepository.findById(id)
                .orElseThrow(
                        () -> new Exception("No student with that id was found.")
                );

        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setRetired(studentDto.isRetired());
        Student updatedStudent;
        try {
            updatedStudent = studentRepository.save(student);
        } catch (Exception e) {
            throw new Exception("Could not update student, please check all required fields are correct.");
        }
        return new StudentExamDto.StudentDto(student);
    }

    // Delete a student by ID
    public StudentExamDto.StudentDto delete(int id) throws Exception {
        var student = studentRepository.findById(id)
                .orElseThrow(
                        () -> new Exception("No student with that id was found.")
                );

        studentRepository.deleteById(id);
        return new StudentExamDto.StudentDto(student);
    }
}