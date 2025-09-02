package org.booleanuk.app.model.exam;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.student.Student;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int ects;
    private int grade;
    private String date;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Exam(String name, int ects, int grade, String date, Student student) {
        this.name = name;
        this.ects = ects;
        this.grade = grade;
        this.date = date;
        this.student = student;
    }

}
