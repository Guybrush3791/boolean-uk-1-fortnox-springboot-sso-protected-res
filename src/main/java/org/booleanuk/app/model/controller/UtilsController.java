package org.booleanuk.app.model.controller;

import org.booleanuk.app.model.jpa.Exam;
import org.booleanuk.app.model.jpa.Student;
import org.booleanuk.app.model.repository.ExamRepository;
import org.booleanuk.app.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/public/utils")
public class UtilsController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExamRepository examRepository;

    @RequestMapping("initialize")
    public String initializeDatabase() {
        studentRepository.deleteAll();
        examRepository.deleteAll();

        Student student1 = new Student("John", "Doe", "john@doe.com", false);
        Student student2 = new Student("Jane", "Smith", "jane@smith.com", true);
        Student student3 = new Student("Emily", "Johnson", "emily@johnson.com", false);

        List.of(student1, student2, student3).forEach(studentRepository::save);

        Exam exam1 = new Exam("Math", 85, 10, LocalDate.now(), student1);
        Exam exam2 = new Exam("Science", 90, 10, LocalDate.now(), student2);
        Exam exam3 = new Exam("History", 78, 10, LocalDate.now(), student3);
        Exam exam4 = new Exam("English", 88, 10, LocalDate.now(), student1);
        Exam exam5 = new Exam("Art", 92, 10, LocalDate.now(), student2);

        List.of(exam1, exam2, exam3, exam4, exam5).forEach(examRepository::save);

        return "Database initialized";
    }

}
