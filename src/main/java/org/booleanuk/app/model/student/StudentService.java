package org.booleanuk.app.model.student;

import org.booleanuk.app.model.exam.Exam;
import org.booleanuk.app.model.exam.ExamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<StudentDto> getAllStudents() {
        List<Student> studentList = repository.findAll();

        return studentList.stream()
                .map(student -> convertToDTO(student, student.getExamList()))
                .toList();
    }

    public StudentDto getSingleStudent(int id) {
        Optional<Student> optStudent = repository.findById(id);

        if (optStudent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
        }

        return convertToDTO(optStudent.get(), optStudent.get().getExamList());
    }

    public Student addStudent(StudentDto dto) {
        Student student = new Student(
                dto.getFirst_name(),
                dto.getLast_name(),
                dto.getEmail(),
                dto.isRetired()
        );

        return repository.save(student);
    }

    public boolean updateStudent(int id, StudentDto dto) {
        Optional<Student> opt = repository.findById(id);

        if (opt.isPresent()) {
            Student s = opt.get();
            s.setFirst_name(dto.getFirst_name());
            s.setLast_name(dto.getLast_name());
            s.setEmail(dto.getEmail());
            s.setRetired(dto.isRetired());

            repository.save(s);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean studentExists(int id) {
        return repository.existsById(id);
    }

    //    HELPER FUNCTION
    public StudentDto convertToDTO(Student student, List<Exam> examList) {
        List<ExamDto> examDtos = examList.stream()
                .map(exam -> new ExamDto(
                        exam.getName(),
                        exam.getEcts(),
                        exam.getGrade(),
                        exam.getDate()
                ))
                .toList();

        return new StudentDto(
                student.getFirst_name(),
                student.getLast_name(),
                student.getEmail(),
                student.isRetired(),
                examDtos
        );
    }

}
