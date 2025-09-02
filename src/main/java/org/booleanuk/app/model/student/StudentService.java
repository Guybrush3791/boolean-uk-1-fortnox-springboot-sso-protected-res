package org.booleanuk.app.model.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getSingleStudent(int id) {
        Optional<Student> opt = studentRepo.findById(id);

        return opt.orElse(null);
    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public boolean updateStudent(int id, StudentDto dto) {
        Optional<Student> opt = studentRepo.findById(id);

        if (opt.isPresent()) {
            Student s = opt.get();
            s.setFirst_name(dto.getFirst_name());
            s.setLast_name(dto.getLast_name());
            s.setEmail(dto.getEmail());
            s.setRetired(dto.isRetired());

            studentRepo.save(s);
            return true;
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
