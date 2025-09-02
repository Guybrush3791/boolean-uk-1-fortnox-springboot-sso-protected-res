package org.booleanuk.app.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.dto.ExamDto;

import java.time.LocalDate;

@Entity
@Table(name = "exams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int ects;
    private int grade;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;

    public Exam(String name, int ects, int grade, LocalDate date, Student student) {
        setName(name);
        setEcts(ects);
        setGrade(grade);
        setDate(date);
        setStudent(student);
    }

    public Exam(ExamDto examDto) {
        setName(examDto.getName());
        setEcts(examDto.getEcts());
        setGrade(examDto.getGrade());
        setDate(examDto.getDate());
    }

}
