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
    @Autowired
    private StudentService studentService;
    public List<Exam> getAll() {
        return examRepository.findAll();
    }

    public Exam getById(int id) {
        return examRepository.findById(id).orElse(null);
    }
    public Exam delete(int id) {
        Exam exam = getById(id);
        if(exam!= null) {
            examRepository.delete(exam);
            return exam;
        }
        return null;
    }
    public Exam save(ExamDto.CreateExamDto createDto) {
        Student student = studentService.getById(createDto.getStudentId());
        if (student!= null) {
            Exam exam = new Exam();
            exam.setName(createDto.getName());
            exam.setEcts(createDto.getEcts());
            exam.setGrade(createDto.getGrade());
            exam.setDate(createDto.getDate());
            exam.setStudent(student);
            return examRepository.save(exam);
        }
        return null;
    }
    public Exam updateExam (int id, ExamDto.UpdateExamDto updatedExam) {
        Exam existingExam = getById(id);
        if (existingExam != null) {
            existingExam.setName(updatedExam.getName());
            existingExam.setEcts(updatedExam.getEcts());
            existingExam.setGrade(updatedExam.getGrade());
            existingExam.setDate(updatedExam.getDate());
            return examRepository.save(existingExam);
        }
        return null;

    }
}

