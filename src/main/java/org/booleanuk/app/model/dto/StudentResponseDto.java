package org.booleanuk.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentResponseDto {
  private int id;
  private String firstName;
  private String lastName;
  private String email;
  private boolean retired;
}
