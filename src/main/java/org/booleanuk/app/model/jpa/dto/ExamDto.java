package org.booleanuk.app.model.jpa.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.jpa.pojo.Exam;
import org.booleanuk.app.model.jpa.pojo.Student;

import java.time.LocalDate;

public class ExamDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateExamDto {

        private String name;
        private int ects;
        private int grade;
        private LocalDate date;
        private int studentId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateExamDto {

        private String name;
        private int ects;
        private int grade;
        private LocalDate date;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExamResponseDto {
        private int id;
        private String name;
        private int ects;
        private int grade;
        private LocalDate date;
        private StudentSummaryDto student; // Controlled student data

        public ExamResponseDto(Exam exam) {
            setId(exam.getId());
            setName(exam.getName());
            setEcts(exam.getEcts());
            setGrade(exam.getGrade());
            setDate(exam.getDate());
            setStudent(new StudentSummaryDto(exam.getStudent()));

        }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StudentSummaryDto {
        private int id;
        private String firstName;
        private String lastName;

        public StudentSummaryDto(Student student) {
            this.id = student.getId();
            this.firstName = student.getFirstName();
            this.lastName = student.getLastName();
            // Notice: No email, no retired status, no exams list
            // Only what's needed for exam context
        }

        }
    }
}
