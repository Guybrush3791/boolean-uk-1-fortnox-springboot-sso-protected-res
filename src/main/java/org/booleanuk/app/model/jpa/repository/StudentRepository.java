package org.booleanuk.app.model.jpa.repository;

import org.booleanuk.app.model.jpa.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
