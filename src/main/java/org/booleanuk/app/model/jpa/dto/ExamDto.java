package org.booleanuk.app.model.jpa.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.jpa.pojo.Exam;
import org.booleanuk.app.model.jpa.pojo.Student;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class ExamDto{

    private int id;
    private String name;
    private int ects;
    private int grade;
    private LocalDate date;
    private Student student;

    public ExamDto(Exam exam) {
        setId(exam.getId());
        setName(exam.getName());
        setEcts(exam.getEcts());
        setGrade(exam.getGrade());
        setDate(exam.getDate());
        setStudent(exam.getStudent());
    }
}
