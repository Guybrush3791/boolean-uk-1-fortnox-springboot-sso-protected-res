package org.booleanuk.app.model.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private String first_name;
    private String last_name;
    private String email;
    private boolean retired;

}
