package org.booleanuk.app.model.jpa.service;

import org.booleanuk.app.model.jpa.dto.StudentDto;
import org.booleanuk.app.model.jpa.pojo.Student;
import org.booleanuk.app.model.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
     public Student delete(Student student) {
         studentRepository.delete(student);
         return student;
     }
     public Student save(Student student) {
         return studentRepository.save(student);
     }
}
