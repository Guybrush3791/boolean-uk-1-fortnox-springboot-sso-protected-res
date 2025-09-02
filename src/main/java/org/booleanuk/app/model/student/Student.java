package org.booleanuk.app.model.student;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String first_name;
    private String last_name;
    private String email;
    private boolean retired;

    public Student(String first_name, String last_name, String email, boolean retired) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.retired = retired;
    }
}
