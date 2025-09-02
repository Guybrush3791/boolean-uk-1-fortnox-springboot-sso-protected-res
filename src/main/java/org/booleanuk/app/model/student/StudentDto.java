package org.booleanuk.app.model.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.exam.Exam;
import org.booleanuk.app.model.exam.ExamDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    private String first_name;
    private String last_name;
    private String email;
    private boolean retired;
    private List<ExamDto> examList;

    public StudentDto(String first_name, String last_name, String email, boolean retired) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.retired = retired;
    }
}
