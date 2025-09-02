package org.booleanuk.app.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.dto.StudentDto;

import java.util.List;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String firstName;
  private String lastName;
  private String email;
  private boolean retired;

  @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
  List<Exam> exams;

  public Student(String firstName, String lastName, String email, boolean retired) {
    setFirstName(firstName);
    setLastName(lastName);
    setEmail(email);
    setRetired(retired);
  }

  public Student(StudentDto studentDto) {
    setFirstName(studentDto.getFirstName());
    setLastName(studentDto.getLastName());
    setEmail(studentDto.getEmail());
    setRetired(studentDto.isRetired());
  }
}
