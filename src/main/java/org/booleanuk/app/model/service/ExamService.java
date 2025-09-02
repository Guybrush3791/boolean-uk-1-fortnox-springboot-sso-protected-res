package org.booleanuk.app.model.service;

import org.booleanuk.app.model.dto.ExamDto;
import org.booleanuk.app.model.dto.StudentExamDto;
import org.booleanuk.app.model.jpa.Exam;
import org.booleanuk.app.model.repository.ExamRepository;
import org.booleanuk.app.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ExamService(ExamRepository examRepository, StudentRepository studentRepository) {
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
    }

    // Methods
    // Get all exams
    public List<StudentExamDto.ExamDto> getAll() {
        return examRepository.findAll()
                .stream()
                .map(StudentExamDto.ExamDto::new)
                .toList();
    }

    // Get exam by ID
    public StudentExamDto.ExamDto getById(int id) throws Exception {
        return examRepository.findById(id)
                .map(StudentExamDto.ExamDto::new)
                .orElseThrow(
                        () -> new Exception("No exam with that id was found.")
                );
    }

    // Save a new student
    public StudentExamDto.ExamDto save(ExamDto examDto) throws Exception {
        Exam exam;
        try {
            exam = new Exam(examDto);
            exam.setStudent(studentRepository.findById(examDto.getStudentId()).orElseThrow(
                    () -> new Exception("No student with that id was found.")
            ));
            exam = examRepository.save(exam);
        } catch (Exception e) {
            throw new Exception("Could not create exam, please check all required fields are correct.");
        }
        return new StudentExamDto.ExamDto(exam);
    }

    // Update an existing exam
    public StudentExamDto.ExamDto update(int id, ExamDto examDto) throws Exception {
        var exam = examRepository.findById(id)
                .orElseThrow(
                        () -> new Exception("No exam with that id was found.")
                );

        exam.setName(examDto.getName());
        exam.setGrade(examDto.getGrade());
        exam.setEcts(examDto.getEcts());
        exam.setDate(examDto.getDate());
        exam.setStudent(studentRepository.findById(examDto.getStudentId()).orElseThrow(
                () -> new Exception("No student with that id was found.")
        ));
        Exam updatedExam;
        try {
            updatedExam = examRepository.save(exam);
        } catch (Exception e) {
            throw new Exception("Could not update exam, please check all required fields are correct.");
        }
        return new StudentExamDto.ExamDto(updatedExam);
    }

    // Delete a student by ID
    public StudentExamDto.ExamDto delete(int id) throws Exception {
        var exam = examRepository.findById(id)
                .orElseThrow(
                        () -> new Exception("No exam with that id was found.")
                );

        studentRepository.deleteById(id);
        return new StudentExamDto.ExamDto(exam);
    }
}
