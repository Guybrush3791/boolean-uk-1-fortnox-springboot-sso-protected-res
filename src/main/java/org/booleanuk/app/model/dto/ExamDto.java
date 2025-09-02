package org.booleanuk.app.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ExamDto {

    private String name;
    private int ects;
    private int grade;
    private LocalDate date;
    @JsonProperty("student_id")
    private int studentId;

}
