package org.booleanuk.app.model.repository;

import org.booleanuk.app.model.jpa.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {}
