package org.booleanuk.app.model.exam;

import org.booleanuk.app.model.student.Student;
import org.booleanuk.app.model.student.StudentDto;
import org.booleanuk.app.model.student.StudentRepository;
import org.booleanuk.app.model.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository repository;
    @Autowired
    private StudentRepository studentRepository;

    public List<ExamDto> getAllExams() {
        List<Exam> examList = repository.findAll();

        return examList.stream().map(exam ->
                convertToDTO(exam, exam.getStudent())).toList();

//        return examList.stream().map(exam -> {
//            StudentDto studentDto = new StudentDto(
//                    exam.getStudent().getFirst_name(),
//                    exam.getStudent().getLast_name(),
//                    exam.getStudent().getEmail(),
//                    exam.getStudent().isRetired()
//            );
//            return new ExamDto(
//                    exam.getName(), exam.getEcts(),
//                    exam.getGrade(), exam.getDate(),
//                    studentDto
//            );
//        }).toList();
    }

    public ExamDto getSingleExam(int id) {
        Optional<Exam> optionalExam = repository.findById(id);

        if (optionalExam.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam not found.");
        }

        return convertToDTO(optionalExam.get(), optionalExam.get().getStudent());
    }

    public ExamDto addExam(ExamDto dto) {
        if (studentRepository.existsById(dto.getStudent_id())) {
            Optional<Student> student = studentRepository.findById(dto.getStudent_id());

            if (student.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
            }

            Exam exam = new Exam(
                    dto.getName(), dto.getEcts(),
                    dto.getGrade(), dto.getDate(),
                    student.get()
            );
            repository.save(exam);

            return convertToDTO(exam, student.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student ID does not exist");
    }

    public boolean updateExam(int id, ExamDto dto) {
        Optional<Exam> opt = repository.findById(id);

        if (opt.isPresent()) {
            Exam exam = opt.get();

            exam.setName(dto.getName());
            exam.setEcts(dto.getEcts());
            exam.setGrade(dto.getGrade());
            exam.setDate(dto.getDate());

            repository.save(exam);
            return true;
        }
        return false;
    }

    public boolean deleteExam(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

//    HELPER FUNCTION
    public ExamDto convertToDTO(Exam exam, Student student) {
        StudentDto studentDto = new StudentDto(
                student.getFirst_name(),
                student.getLast_name(),
                student.getEmail(),
                student.isRetired()
        );

        return new ExamDto(
                exam.getName(),
                exam.getEcts(),
                exam.getGrade(),
                exam.getDate(),
                studentDto
        );
    }
}
