package com.learn.jarvis.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StudentDTO implements Serializable {
  private String firstName;
  private String lastName;
  private Integer marks;
}
