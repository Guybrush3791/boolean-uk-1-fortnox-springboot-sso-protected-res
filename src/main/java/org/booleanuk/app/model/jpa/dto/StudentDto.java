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

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean retired;
    private List<Exam> exams;

    public StudentDto(Student student) {
        setFirstName(student.getFirstName());
        setLastName(student.getLastName());
        setEmail(student.getEmail());
        setRetired(student.isRetired());
        setExams(student.getExams());
    }

}
