package com.learn.jarvis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentDTO {
  private Integer id;
  private String firstName;
  private String lastName;
  private Integer marks;
}
