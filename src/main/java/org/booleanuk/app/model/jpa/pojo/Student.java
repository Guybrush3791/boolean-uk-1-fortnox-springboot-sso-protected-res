package org.booleanuk.app.model.jpa.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.jpa.dto.StudentDto;

@Entity
@Data
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "retired")
    private boolean retired;

    public Student (StudentDto studentDto) {
        setFirstName(studentDto.getFirstName());
        setLastName(studentDto.getLastName());
        setEmail(studentDto.getEmail());
        setRetired(studentDto.isRetired());


    }
}
