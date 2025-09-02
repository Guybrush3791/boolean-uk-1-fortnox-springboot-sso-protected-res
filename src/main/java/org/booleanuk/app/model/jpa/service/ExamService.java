package org.booleanuk.app.model.jpa.service;

import org.booleanuk.app.model.jpa.dto.ExamDto;
import org.booleanuk.app.model.jpa.pojo.Exam;
import org.booleanuk.app.model.jpa.pojo.Student;
import org.booleanuk.app.model.jpa.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    public List<Exam> getAll() {
        return examRepository.findAll();
    }

    public Exam getById(int id) {
        return examRepository.findById(id).orElse(null);
    }
    public Exam delete(Exam exam) {
        examRepository.delete(exam);
        return exam;
    }
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }
}

