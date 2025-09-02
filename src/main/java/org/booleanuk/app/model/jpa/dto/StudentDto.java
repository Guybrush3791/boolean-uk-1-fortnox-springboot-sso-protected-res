package org.booleanuk.app.model.jpa.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.jpa.pojo.Exam;
import org.booleanuk.app.model.jpa.pojo.Student;

import java.time.LocalDate;
import java.util.List;


public class StudentDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateStudentDto {

        private String firstName;
        private String lastName;
        private String email;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateStudentDto {

        private String firstName;
        private String lastName;
        private String email;
        private boolean retired;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StudentResponseDto {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private boolean retired;
        private List<ExamSummaryDto> exams;

        public StudentResponseDto(Student student) {
            setId(student.getId());
            setLastName(student.getLastName());
            setFirstName(student.getFirstName());
            setEmail(student.getEmail());
            setRetired(student.isRetired());
            setExams(student.getExams().stream()
                    .map(ExamSummaryDto::new)
                    .toList());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExamSummaryDto  {
        private int id;
        private String name;
        private int grade;
        private LocalDate date;

        public ExamSummaryDto(Exam exam) {
            setId(exam.getId());
            setName(exam.getName());
            setGrade(exam.getGrade());
            setDate(exam.getDate());
        }
    }

}
