package org.booleanuk.app.model.jpa.repository;

import org.booleanuk.app.model.jpa.pojo.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {
}
