package org.booleanuk.app.model.exam;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.student.StudentDto;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamDto {

    private String name;
    private int ects;
    private int grade;
    private String date;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int student_id;
    private StudentDto studentDto;

    public ExamDto(String name, int ects, int grade, String date, StudentDto studentDto) {
        this.name = name;
        this.ects = ects;
        this.grade = grade;
        this.date = date;
        this.studentDto = studentDto;
    }

    public ExamDto(String name, int ects, int grade, String date) {
        this.name = name;
        this.ects = ects;
        this.grade = grade;
        this.date = date;
    }

}
