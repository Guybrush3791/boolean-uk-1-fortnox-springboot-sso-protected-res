package org.booleanuk.app.model.jpa.service;

import org.booleanuk.app.model.jpa.dto.StudentDto;
import org.booleanuk.app.model.jpa.pojo.Student;
import org.booleanuk.app.model.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student delete(int id) {
        Student student = getById(id);
        if(student != null) {
            studentRepository.delete(student);
            return student;
        }
        return null;
    }

    public Student save(StudentDto.CreateStudentDto createStudent) {
        Student student = new Student();
        if (student != null) {
            student.setFirstName(createStudent.getFirstName());
            student.setLastName(createStudent.getLastName());
            student.setEmail(createStudent.getEmail());
            student.setRetired(false);
            return studentRepository.save(student);
        }
        return null;
    }

    public Student updateStudent(int id, StudentDto.UpdateStudentDto updateStudent) {

        Student student = getById(id);
        if (student != null) {
            student.setFirstName(updateStudent.getFirstName());
            student.setLastName(updateStudent.getLastName());
            student.setEmail(updateStudent.getEmail());
            student.setRetired(updateStudent.isRetired());
            return studentRepository.save(student);
        }
        return null;
    }

}

