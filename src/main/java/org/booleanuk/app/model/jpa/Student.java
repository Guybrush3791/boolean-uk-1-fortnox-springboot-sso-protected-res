package org.booleanuk.app.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name ="retired")
    private boolean retired;

    public Student(String firstName, String lastName, String email, boolean isRetired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.retired = retired;

    }

}
