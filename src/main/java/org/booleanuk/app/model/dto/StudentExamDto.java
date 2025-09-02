package org.booleanuk.app.model.dto;

import lombok.Data;
import org.booleanuk.app.model.jpa.Exam;
import org.booleanuk.app.model.jpa.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentExamDto {

    @Data
    public static class StudentDto {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private boolean retired;

        private List<ExamDto> exams;

        public StudentDto(Student student) {
            setId(student.getId());
            setFirstName(student.getFirstName());
            setLastName(student.getLastName());
            setEmail(student.getEmail());
            setRetired(student.isRetired());
            setExams(
                    Optional.ofNullable(student.getExams())
                            .orElseGet(ArrayList::new)
                            .stream()
                            .map(ExamDto::new)
                            .toList()
            );
        }
    }

    @Data
    public static class ExamDto {
        private int id;
        private String name;
        private int ects;
        private int grade;
        private LocalDate date;
        private String studentName;

        public ExamDto(Exam exam) {
            setId(exam.getId());
            setName(exam.getName());
            setEcts(exam.getEcts());
            setGrade(exam.getGrade());
            setDate(exam.getDate());
            setStudentName(exam.getStudent().getFirstName().concat(" ").concat(exam.getStudent().getLastName()));
        }
    }
}
