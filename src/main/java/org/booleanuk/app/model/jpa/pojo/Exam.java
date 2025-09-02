package org.booleanuk.app.model.jpa.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.jpa.dto.ExamDto;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "exams")
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "ects")
    private int ects;
    @Column(name = "grade")
    private int grade;
    @Column(name= "date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name="student_id", nullable = false)
    @JsonBackReference
    private Student student;


}
